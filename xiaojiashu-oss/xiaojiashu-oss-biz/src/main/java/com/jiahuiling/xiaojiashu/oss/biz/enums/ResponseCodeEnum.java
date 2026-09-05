package com.jiahuiling.xiaojiashu.oss.biz.enums;

import com.jiahuiling.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
//    通用业务状态码
    SYSTEM_ERROR("OSS-10000","出错啦，小贾同学努力修bug中"),
    PARAM_NOT_VALID("OSS-10001","参数错误"),
    ;

//    异常码，异常信息
    private final String errorCode;
    private final String errorMessage;
}
