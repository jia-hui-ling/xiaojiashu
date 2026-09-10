package com.jiahuiling.xiaojiashu.kv.biz.controller;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.kv.biz.service.NoteContentService;
import com.jiahuiling.xiaojiashu.kv.dto.req.AddNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.DeleteNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.req.FindNoteContentReqDTO;
import com.jiahuiling.xiaojiashu.kv.dto.rsp.FindNoteContentRspDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 贾慧玲
 * @Date 2026/9/10 17:01
 * @Description 内容
 */

@RestController
@RequestMapping("/kv")
@Slf4j
public class NoteContentController {
    @Resource
    private NoteContentService noteContentService;

    @PostMapping("/note/content/add")
    public Response<?> addNoteContent(@Validated @RequestBody AddNoteContentReqDTO addNoteContentReqDTO){
        return noteContentService.addNoteContent(addNoteContentReqDTO);
    }

    @PostMapping("/note/content/find")
    public Response<FindNoteContentRspDTO> findNoteContent(@Validated @RequestBody FindNoteContentReqDTO findNoteContentReqDTO){
        return noteContentService.findNoteContent(findNoteContentReqDTO);
    }

    @PostMapping("/note/content/delete")
    public Response<?> deleteNoteContent(@Validated @RequestBody DeleteNoteContentReqDTO deleteNoteContentReqDTO){
        return noteContentService.deleteNoteContent(deleteNoteContentReqDTO);
    }
}
