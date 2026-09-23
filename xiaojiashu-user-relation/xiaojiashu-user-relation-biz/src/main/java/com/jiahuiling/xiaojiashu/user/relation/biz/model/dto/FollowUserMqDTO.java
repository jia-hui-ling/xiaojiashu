package com.jiahuiling.xiaojiashu.user.relation.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: 关注用户
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowUserMqDTO {
    private Long userId;

    private Long followUserId;

    private LocalDateTime createTime;

}
