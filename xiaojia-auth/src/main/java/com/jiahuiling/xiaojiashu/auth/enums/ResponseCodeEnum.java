package com.jiahuiling.xiaojiashu.auth.enums;

import com.jiahuiling.framework.common.exception.BaseExceptionInterFace;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterFace {
//    通用业务状态码

    SYSTEM_ERROR("AUTH-10000","出错了，小贾同学正在努力修复bug中"),
    PARAM_NOT_VALID("AUTH-10001","参数错误"),
    VERIFICATION_CODE_SEND_FREQUENTLY("AYTH-20000","请求频繁，请3分钟后重试"),
    ;

//    异常码，异常信息
    private final String errorCode;
    private final String errorMessage;
}
