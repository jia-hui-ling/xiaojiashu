package com.jiahuiling.xiaojiashu.auth.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotBlank(message = "0000")
    private String nickname;
    LocalDateTime createTime;

}
