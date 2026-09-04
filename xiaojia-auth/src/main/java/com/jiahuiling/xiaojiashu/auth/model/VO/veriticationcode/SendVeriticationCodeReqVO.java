package com.jiahuiling.xiaojiashu.auth.model.VO.veriticationcode;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 12:30
 * @Description TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendVeriticationCodeReqVO {
    @NotBlank(message = "手机号不能为空")
    String phone;
}
