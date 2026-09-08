package com.jiahuiling.xiaojiashu.user.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 18:52
 * @Description TODO
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {
    YES(true),
    NO(false),
    ;
    private final Boolean value;
}
