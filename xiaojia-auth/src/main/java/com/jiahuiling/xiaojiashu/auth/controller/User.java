package com.jiahuiling.xiaojiashu.auth.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 17:57
 * @Description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String nickname;
    LocalDateTime createTime;

}
