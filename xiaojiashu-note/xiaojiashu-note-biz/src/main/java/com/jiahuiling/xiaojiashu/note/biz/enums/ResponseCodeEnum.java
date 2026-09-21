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
    NOTE_TYPE_ERROR("NOTE-20000", "未知的笔记类型"),
    NOTE_PUBLISH_FAIL("NOTE-20001", "笔记发布失败"),
    NOTE_NOT_FOUND("NOTE-20002", "笔记不存在"),
    NOTE_PRIVATE("NOTE-20003", "作者已将该笔记设置为仅自己可见"),
    NOTE_UPDATE_FAIL("NOTE-20004", "笔记更新失败"),
    TOPIC_NOT_FOUND("NOTE-20005","话题不存在")
    ;

//    异常码，异常信息
    private final String errorCode;
    private final String errorMessage;
}
