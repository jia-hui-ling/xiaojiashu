package com.jiahuiling.xiaojiashu.auth.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.model.VO.veriticationcode.SendVerificationCodeReqVO;

public interface VerificationCodeService {
    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}
