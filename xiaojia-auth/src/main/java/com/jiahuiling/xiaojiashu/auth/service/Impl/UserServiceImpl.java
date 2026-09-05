package com.jiahuiling.xiaojiashu.auth.service.Impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.framework.jackson.util.JsonUtils;
import com.jiahuiling.xiaojiashu.auth.constant.RedisKeyConstants;
import com.jiahuiling.xiaojiashu.auth.constant.RoleConstants;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.RoleDO;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserDO;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserRoleDO;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.RoleDOMapper;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.RolePermissionDOMapper;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.UserDOMapper;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.UserRoleDOMapper;
import com.jiahuiling.xiaojiashu.auth.enums.DeletedEnum;
import com.jiahuiling.xiaojiashu.auth.enums.LoginTypeEnum;
import com.jiahuiling.xiaojiashu.auth.enums.ResponseCodeEnum;
import com.jiahuiling.xiaojiashu.auth.enums.StatusEnum;
import com.jiahuiling.xiaojiashu.auth.filter.LoginUserContextHolder;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UpdatePasswordReqVO;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UserLoginReqVO;
import com.jiahuiling.xiaojiashu.auth.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 18:30
 * @Description TODO
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private UserRoleDOMapper userRoleDOMapper;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 登录与注册
     *
     * @param userLoginReqVO
     * @return
     */
    @Override
    public Response<String> LoginAndRegister(UserLoginReqVO userLoginReqVO) {
        String phone = userLoginReqVO.getPhone();
        Integer type = userLoginReqVO.getType();
        Long userId = null;
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(type);
        switch (loginTypeEnum) {
            case VERIFICATION_CODE:
                String verificationCode = userLoginReqVO.getCode();
                Preconditions.checkArgument(StringUtils.isNoneBlank(verificationCode), "验证码不能为空");
                String key = RedisKeyConstants.buildVerificationCodeKey(phone);
                String sentCode = (String) redisTemplate.opsForValue().get(key);
                if (!StringUtils.equals(verificationCode, sentCode)) {
                    return Response.fail(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
                }
                UserDO userDO = userDOMapper.selectByPhone(phone);
                log.info("==> 用户是否注册, phone: {}, userDO: {}", phone, JsonUtils.toJsonString(userDO));
                if (Objects.isNull(userDO)) {
                    userId = registerUser(phone);
                } else {
                    userId = userDO.getId();
                }

                break;
            case PASSWORD:
                //todo
                break;
            default:
                break;
        }
        StpUtil.login(userId);

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return Response.success(tokenInfo.getTokenValue());
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public Response<?> logout() {
        Long userId = LoginUserContextHolder.getUserId();
        return Response.success();
    }

    /**
     * 修改密码
     *
     * @param updatePasswordReqVO
     * @return
     */
    @Override
    public Response<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO) {
        // 新密码
        String newPassword = updatePasswordReqVO.getNewPassword();
        // 密码加密
        String encodePassword = passwordEncoder.encode(newPassword);

        // 获取当前请求对应的用户 ID
        Long userId = LoginUserContextHolder.getUserId();

        UserDO userDO = UserDO.builder()
                .id(userId)
                .password(encodePassword)
                .updateTime(LocalDateTime.now())
                .build();
        // 更新密码
        userDOMapper.updateByPrimaryKeySelective(userDO);

        return Response.success();
    }

    /**
     * 系统自动注册用户
     *
     * @param phone
     * @return
     */
    public Long registerUser(String phone) {
        return transactionTemplate.execute(status -> {
            try {
                Long xiaojiashuId = redisTemplate.opsForValue().increment(RedisKeyConstants.XIAOJIASHU_ID_GENERATOR_KEY);

                UserDO userDO = UserDO.builder()
                        .phone(phone)
                        .xiaojiashuId(String.valueOf(xiaojiashuId))
                        .nickname("喜欢小贾的" + xiaojiashuId)
                        .status(StatusEnum.ENABLE.getValue())
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .isDeleted(DeletedEnum.NO.getValue())
                        .build();

                userDOMapper.insert(userDO);

                Long userId = userDO.getId();

                UserRoleDO userRoleDO = UserRoleDO.builder()
                        .userId(userId)
                        .roleId(RoleConstants.COMMON_USER_ID)
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .isDeleted(DeletedEnum.NO.getValue())
                        .build();

                userRoleDOMapper.insert(userRoleDO);

                RoleDO roleDO = roleDOMapper.selectByPrimaryKey(RoleConstants.COMMON_USER_ID);
                List<String> roles = new ArrayList(1);
                roles.add(roleDO.getRoleKey());
                String userRolesKey = RedisKeyConstants.buildUserRoleKey(userId);
                redisTemplate.opsForValue().set(userRolesKey, JsonUtils.toJsonString(roles));

                return userId;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("==> 系统注册用户异常", e);
                return null;
            }
        });
    }
}
