package com.jiahuiling.xiaojiashu.user.biz.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.biz.model.vo.UpdateUserInfoReqVO;

public interface UserService {

    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);
}

