package com.jiahuiling.xiaojiashu.auth.service.Impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.jiahuiling.framework.common.exception.BizException;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.constant.RedisKeyConstants;
import com.jiahuiling.xiaojiashu.auth.enums.LoginTypeEnum;
import com.jiahuiling.xiaojiashu.auth.enums.ResponseCodeEnum;
import com.jiahuiling.xiaojiashu.auth.filter.LoginUserContextHolder;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UpdatePasswordReqVO;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UserLoginReqVO;
import com.jiahuiling.xiaojiashu.auth.rpc.UserRpcService;
import com.jiahuiling.xiaojiashu.auth.service.AuthService;
import com.jiahuiling.xiaojiashu.user.dto.resp.FindUserByPhoneRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 18:30
 * @Description TODO
 */

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRpcService userRpcService;
    ;

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
        if (Objects.isNull(loginTypeEnum)) {
            throw new BizException(ResponseCodeEnum.LOGIN_TYPE_ERROR);
        }
        switch (loginTypeEnum) {

            case VERIFICATION_CODE://验证码
                String verificationCode = userLoginReqVO.getCode();
                Preconditions.checkArgument(StringUtils.isNoneBlank(verificationCode), "验证码不能为空");
                String key = RedisKeyConstants.buildVerificationCodeKey(phone);
                String sentCode = (String) redisTemplate.opsForValue().get(key);
                if (!StringUtils.equals(verificationCode, sentCode)) {
                    throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
                }
                // RPC: 调用用户服务，注册用户
                Long userIdTmp = userRpcService.registerUser(phone);

                // 若调用用户服务，返回的用户 ID 为空，则提示登录失败
                if (Objects.isNull(userIdTmp)) {
                    throw new BizException(ResponseCodeEnum.LOGIN_FAIL);
                }

                userId = userIdTmp;
                break;

            case PASSWORD:
                String password = userLoginReqVO.getPassword();
                FindUserByPhoneRspDTO findUserByPhoneRspDTO = userRpcService.findUserByPhone(phone);
                // 判断该手机号是否注册
                if (Objects.isNull(findUserByPhoneRspDTO)) {
                    throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
                }

                // 拿到密文密码
                String encodePassword = findUserByPhoneRspDTO.getPassword();

                // 匹配密码是否一致
                boolean isPasswordCorrect = passwordEncoder.matches(password, encodePassword);

                // 如果不正确，则抛出业务异常，提示用户名或者密码不正确
                if (!isPasswordCorrect) {
                    throw new BizException(ResponseCodeEnum.PHONE_OR_PASSWORD_ERROR);
                }

                userId = findUserByPhoneRspDTO.getId();
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

        // RPC: 调用用户服务：更新密码
        userRpcService.updatePassword(encodePassword);

        return Response.success();
    }
}
