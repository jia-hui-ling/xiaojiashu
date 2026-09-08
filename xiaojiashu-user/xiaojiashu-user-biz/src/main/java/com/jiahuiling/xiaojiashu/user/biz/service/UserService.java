package com.jiahuiling.xiaojiashu.user.biz.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.biz.model.vo.UpdateUserInfoReqVO;
import com.jiahuiling.xiaojiashu.user.dto.req.FindUserByPhoneReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.req.RegisterUserReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.req.UpdateUserPasswordReqDTO;
import com.jiahuiling.xiaojiashu.user.dto.resp.FindUserByPhoneRspDTO;

public interface UserService {

    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    Response<Long> register(RegisterUserReqDTO registerUserReqDTO);

    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByPhoneReqDTO
     * @return
     */
    Response<FindUserByPhoneRspDTO> findByPhone(FindUserByPhoneReqDTO findUserByPhoneReqDTO);

    /**
     * 更新密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    Response<?> updatePassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
}

