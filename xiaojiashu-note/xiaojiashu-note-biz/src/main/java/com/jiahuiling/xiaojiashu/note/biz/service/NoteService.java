package com.jiahuiling.xiaojiashu.note.biz.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.FindNoteDetailReqVO;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.FindNoteDetailRspVO;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.PublishNoteReqVO;
import com.jiahuiling.xiaojiashu.note.biz.model.vo.UpdateNoteReqVO;

public interface NoteService {
    /**
     * 笔记发布
     * @param publishNoteReqVO
     * @return
     */
    Response<?> publishNote(PublishNoteReqVO publishNoteReqVO);

    /**
     * 笔记详情
     * @param findNoteDetailReqVO
     * @return
     */
    Response<FindNoteDetailRspVO> findNoteDetail(FindNoteDetailReqVO findNoteDetailReqVO);

    /**
     * 笔记更新
     * @param updateNoteReqVO
     * @return
     */
    Response<?> updateNote(UpdateNoteReqVO updateNoteReqVO);

}
