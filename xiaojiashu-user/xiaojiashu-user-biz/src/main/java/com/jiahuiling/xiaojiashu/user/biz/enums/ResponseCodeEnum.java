package com.jiahuiling.xiaojiashu.user.biz.enums;

import com.jiahuiling.framework.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
//    通用业务状态码

    SYSTEM_ERROR("USER-10000","出错了，小贾同学正在努力修复bug中"),
    PARAM_NOT_VALID("USER-10001","参数错误"),
    NICK_NAME_VALID_FAIL("USER-20001", "昵称请设置2-24个字符，不能使用@《/等特殊字符"),
    XIAOJIASHU_ID_VALID_FAIL("USER-20002", "小哈书号请设置6-15个字符，仅可使用英文（必须）、数字、下划线"),
    SEX_VALID_FAIL("USER-20003", "性别错误"),
    INTRODUCTION_VALID_FAIL("USER-20004", "个人简介请设置1-100个字符"),
    UPLOAD_AVATAR_FAIL("USER-20005", "头像上传失败"),
    UPLOAD_BACKGROUND_IMG_FAIL("USER-20006", "背景图上传失败"),
    ;

//    异常码，异常信息
    private final String errorCode;
    private final String errorMessage;
}
