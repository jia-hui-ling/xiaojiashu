package com.jiahuiling.xiaojiashu.kv.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @Author 贾慧玲
 * @Date 2026/9/10 17:41
 * @description: 笔记内容
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteContentRspDTO {
    /**
     * 笔记 ID
     */
    private UUID noteId;

    /**
     * 笔记内容
     */
    private String content;
}
