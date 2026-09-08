package com.jiahuiling.xiaojiashu.user.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 10:50
 * @Description 性别
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    WOMAN(0),
    MAN(1),
    ;
    private final Integer value;

    public static boolean isValid(Integer value) {
        for (SexEnum loginTypeEnum : SexEnum.values()) {
            if (Objects.equals(value,loginTypeEnum.getValue())) {
                return true;
            }
        }
        return false;
    }
}
