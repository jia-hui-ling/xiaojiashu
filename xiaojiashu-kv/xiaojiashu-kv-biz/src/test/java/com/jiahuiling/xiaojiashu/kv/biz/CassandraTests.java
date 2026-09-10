package com.jiahuiling.xiaojiashu.kv.biz;

import com.jiahuiling.framework.jackson.util.JsonUtils;
import com.jiahuiling.xiaojiashu.kv.biz.domain.dataobject.NoteContentDO;
import com.jiahuiling.xiaojiashu.kv.biz.domain.repository.NoteContentRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author 贾慧玲
 * @Date 2026/9/10 11:14
 * @Description TODO
 */
@SpringBootTest
@Slf4j
public class CassandraTests {
    @Resource
    private NoteContentRepository noteContentRepository;

    /**
     * 测试插入数据
     */
    @Test
    public void testInsert() {
        NoteContentDO noteContentDO = NoteContentDO.builder()
                .id(UUID.randomUUID())
                .content("代码")
                .build();
        noteContentRepository.insert(noteContentDO);
    }

    /**
     * 测试查询数据
     */
    @Test
    void testSelect() {
        Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString("b43025d0-ff8d-49a2-b36c-c8d114c11e73"));
        optional.ifPresent(noteContentDO -> log.info("查询结果：{}", JsonUtils.toJsonString(noteContentDO)));
    }

    /**
     * 测试删除数据
     */
    @Test
    void testDelete() {
        noteContentRepository.deleteById(UUID.fromString("eaad1222-f091-40be-b824-0c9f275724a7"));
    }


}
