package com.jiahuiling.xiaojiashu.auth.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserDO;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UserLoginReqVO;

public interface UserService {
    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    Response<String> LoginAndRegister(UserLoginReqVO userLoginReqVO);
    Response<?> logout(Long userId);
}
