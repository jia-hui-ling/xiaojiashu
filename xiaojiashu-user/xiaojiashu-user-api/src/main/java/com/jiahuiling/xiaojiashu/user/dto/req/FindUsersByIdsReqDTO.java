package com.jiahuiling.xiaojiashu.user.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUsersByIdsReqDTO {

    @NotNull(message = "用户信息不能为空")
    @Size(min = 1,max = 10,message = "用户ID集合大小必须大于等于1,小于等于10")
    private List<Long> ids;
}
