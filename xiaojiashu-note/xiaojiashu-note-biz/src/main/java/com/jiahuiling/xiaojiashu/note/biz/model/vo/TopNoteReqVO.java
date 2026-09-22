package com.jiahuiling.xiaojiashu.note.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 笔记置顶/取消置顶
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopNoteReqVO {

    @NotNull(message = "笔记 ID 不能为空")
    private Long id;

    @NotNull(message = "置顶状态不能为空")
    private Boolean isTop;
}
