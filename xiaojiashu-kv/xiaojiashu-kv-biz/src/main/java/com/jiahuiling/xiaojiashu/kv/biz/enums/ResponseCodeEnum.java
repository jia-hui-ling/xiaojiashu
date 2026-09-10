package com.jiahuiling.xiaojiashu.kv.biz.enums;

import com.jiahuiling.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("KV-10000", "出错啦，小贾同学正在努力修复中..."),
    PARAM_NOT_VALID("KV-10001", "参数错误"),

    // ----------- 业务异常状态码 -----------
    NOTE_CONTENT_NOT_FOUND("KV-20000", "该笔记内容不存在"),
    ;
    private final String errorCode;
    private final String errorMessage;
}
