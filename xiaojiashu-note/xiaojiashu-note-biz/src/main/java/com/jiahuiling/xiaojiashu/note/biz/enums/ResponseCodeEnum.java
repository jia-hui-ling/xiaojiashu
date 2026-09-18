package com.jiahuiling.xiaojiashu.note.biz.enums;

import com.jiahuiling.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
//    通用业务状态码

    SYSTEM_ERROR("NOTE-10000","出错了，小贾同学正在努力修复bug中"),
    PARAM_NOT_VALID("NOTE-10001","参数错误"),
    ;

//    异常码，异常信息
    private final String errorCode;
    private final String errorMessage;
}
