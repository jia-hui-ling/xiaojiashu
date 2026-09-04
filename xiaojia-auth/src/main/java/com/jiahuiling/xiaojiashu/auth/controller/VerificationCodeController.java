package com.jiahuiling.xiaojiashu.auth.controller;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.model.VO.veriticationcode.SendVeriticationCodeReqVO;
import com.jiahuiling.xiaojiashu.auth.service.VerificationCodeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 12:50
 * @Description TODO
 */
@RestController
@Slf4j
public class VerificationCodeController {
    @Resource
    private VerificationCodeService verificationCodeService;

    @PostMapping("/verification/code/send")
    @ApiOperationLog(description = "发送验证码")
    public Response<?> send(@Validated @RequestBody SendVeriticationCodeReqVO sendVeriticationCodeReqVO){
        return verificationCodeService.send(sendVeriticationCodeReqVO);
    }
}
