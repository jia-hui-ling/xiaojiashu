package com.jiahuiling.xiaojiashu.auth.rpc;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.api.UserFeignApi;
import com.jiahuiling.xiaojiashu.user.dto.req.FindUserByPhoneReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.req.RegisterUserReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.req.UpdateUserPasswordReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.resp.FindUserByPhoneRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 20:23
 * @Description 用户服务
 */
@Component
public class UserRpcService {
    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 用户注册
     *
     * @param phone
     * @return
     */
    public Long registerUser(String phone) {
        RegisterUserReqDTO registerUserReqDTO = new RegisterUserReqDTO();
        registerUserReqDTO.setPhone(phone);

        Response<Long> response = userFeignApi.registerUser(registerUserReqDTO);

        if (!response.isSuccess()) {
            return null;
        }
        return response.getData();
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    public FindUserByPhoneRspDTO findUserByPhone(String phone) {
        FindUserByPhoneReqDTO findUserByPhoneReqDTO = new FindUserByPhoneReqDTO();
        findUserByPhoneReqDTO.setPhone(phone);
        Response<FindUserByPhoneRspDTO> response = userFeignApi.findByPhone(findUserByPhoneReqDTO);

        if (!response.isSuccess()) {
            return null;
        }

        return response.getData();
    }

    /**
     * 更新密码
     *
     * @param encodePassword
     */
    public void updatePassword(String encodePassword) {
        UpdateUserPasswordReqDTO updateUserPasswordReqDTO = new UpdateUserPasswordReqDTO();
        updateUserPasswordReqDTO.setEncodePassword(encodePassword);
        Response<?> response = userFeignApi.updatePassword(updateUserPasswordReqDTO);
        }
    }
