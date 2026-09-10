package com.jiahuiling.xiaojiashu.kv.biz.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * @Author 贾慧玲
 * @Date 2026/9/10 11:06
 * @Description TODO
 */
@Table("note_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteContentDO {
    @PrimaryKey("id")
    private UUID id;

    private String content;
}
