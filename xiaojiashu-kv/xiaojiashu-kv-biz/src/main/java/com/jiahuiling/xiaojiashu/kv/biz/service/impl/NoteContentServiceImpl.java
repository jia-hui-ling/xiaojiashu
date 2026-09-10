package com.jiahuiling.xiaojiashu.kv.biz.service.impl;

import com.jiahuiling.framework.common.exception.BizException;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.kv.biz.domain.dataobject.NoteContentDO;
import com.jiahuiling.xiaojiashu.kv.biz.domain.repository.NoteContentRepository;
import com.jiahuiling.xiaojiashu.kv.biz.enums.ResponseCodeEnum;
import com.jiahuiling.xiaojiashu.kv.biz.service.NoteContentService;
import com.jiahuiling.xiaojiashu.kv.dto.req.AddNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.DeleteNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.FindNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.rsp.FindNoteContentRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author 贾慧玲
 * @Date 2026/9/10 16:51
 * @Description Key-Value 业务
 */
@Service
@Slf4j
public class NoteContentServiceImpl implements NoteContentService {
    @Resource
    private NoteContentRepository noteContentRepository;

    @Override
    public Response<?> addNoteContent(AddNoteContentReqDTO addNoteContentReqDTO) {
        Long noteId = addNoteContentReqDTO.getNoteId();
        String content = addNoteContentReqDTO.getContent();
        NoteContentDO nodeContent = NoteContentDO.builder()
                .id(UUID.randomUUID())
                .content(content)
                .build();

        noteContentRepository.save(nodeContent);
        return Response.success();
    }

    @Override
    public Response<FindNoteContentRspDTO> findNoteContent(FindNoteContentReqDTO findNoteContentReqDTO) {
        // 笔记 ID
        String noteId = findNoteContentReqDTO.getNoteId();
        // 根据笔记 ID 查询笔记内容
        Optional<NoteContentDO> optional = noteContentRepository.findById(UUID.fromString(noteId));

        // 若笔记内容不存在
        if (!optional.isPresent()) {
            throw new BizException(ResponseCodeEnum.NOTE_CONTENT_NOT_FOUND);
        }

        NoteContentDO noteContentDO = optional.get();
        // 构建返参 DTO
        FindNoteContentRspDTO findNoteContentRspDTO = FindNoteContentRspDTO.builder()
                .noteId(noteContentDO.getId())
                .content(noteContentDO.getContent())
                .build();

        return Response.success(findNoteContentRspDTO);
    }
    /**
     * 删除笔记内容
     *
     * @param deleteNoteContentReqDTO
     * @return
     */
    @Override
    public Response<?> deleteNoteContent(DeleteNoteContentReqDTO deleteNoteContentReqDTO) {
        // 笔记 ID
        String noteId = deleteNoteContentReqDTO.getNoteId();
        // 删除笔记内容
        noteContentRepository.deleteById(UUID.fromString(noteId));
        return Response.success();
    }

}