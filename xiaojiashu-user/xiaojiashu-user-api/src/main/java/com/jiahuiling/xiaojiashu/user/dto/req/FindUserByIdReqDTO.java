package com.jiahuiling.xiaojiashu.user.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByIdReqDTO {
    /**
     * 用户ID
     */
    @NotNull(message = "用户 ID 不能为空")
    private Long id;
}
