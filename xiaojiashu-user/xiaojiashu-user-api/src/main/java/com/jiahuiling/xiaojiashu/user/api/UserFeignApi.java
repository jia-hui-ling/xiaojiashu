package com.jiahuiling.xiaojiashu.user.api;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.constant.ApiConstants;
import com.jiahuiling.xiaojiashu.user.dto.req.FindUserByPhoneReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.req.RegisterUserReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.req.UpdateUserPasswordReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.resp.FindUserByPhoneRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ApiConstants.SERVICE_NAME)
public interface UserFeignApi {
    String PREFIX = "/user";


    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/register")
    Response<Long> registerUser(@RequestBody RegisterUserReqDTO registerUserReqDTO);

    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByPhoneReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/findByPhone")
    Response<FindUserByPhoneRspDTO> findByPhone(@RequestBody FindUserByPhoneReqDTO findUserByPhoneReqDTO);

    /**
     * 更新密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/password/update")
    Response<?> updatePassword(@RequestBody UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
}


