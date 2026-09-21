package com.jiahuiling.xiaojiashu.note.biz.controller;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.FindNoteDetailReqVO;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.FindNoteDetailRspVO;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.PublishNoteReqVO;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.UpdateNoteReqVO;
import com.jiahuiling.xiaojiashu.note.biz.service.NoteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Resource
    private NoteService noteService;

    @PostMapping("/publish")
    @ApiOperationLog(description = "笔记发布")
    public Response<String> publishNote(@Validated @RequestBody PublishNoteReqVO publishNoteReqVO){
        return noteService.publishNote(publishNoteReqVO);
    }
    @PostMapping("/detail")
    @ApiOperationLog(description = "笔记详情")
    public Response<FindNoteDetailRspVO> findNoteDetail(@Validated @RequestBody FindNoteDetailReqVO findNoteDetailReqVO){
        return noteService.findNoteDetail(findNoteDetailReqVO);
    }
    @PostMapping("/update")
    @ApiOperationLog(description = "笔记更新")
    public Response<?> updateNote(@Validated @RequestBody UpdateNoteReqVO updateNoteReqVO){
        return noteService.updateNote(updateNoteReqVO);
    }
}
