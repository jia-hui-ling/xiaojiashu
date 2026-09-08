package com.jiahuiling.xiaojiashu.auth.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UpdatePasswordReqVO;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UserLoginReqVO;

public interface AuthService {
    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    Response<String> LoginAndRegister(UserLoginReqVO userLoginReqVO);
    /**
     * 退出登录
     * @return
     */
    Response<?> logout();

    /**
     * 修改密码
     * @param updatePasswordReqVO
     * @return
     */
    Response<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO);
}
