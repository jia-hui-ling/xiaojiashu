package com.jiahuiling.xiaojiashu.auth.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 11:22
 * @Description TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDO {
    private Long id;
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
