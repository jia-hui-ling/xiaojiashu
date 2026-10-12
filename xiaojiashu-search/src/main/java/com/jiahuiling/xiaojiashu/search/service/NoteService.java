package com.jiahuiling.xiaojiashu.search.service;

import com.jiahuiling.framework.common.response.PageResponse;
import com.jiahuiling.xiaojiashu.search.model.vo.SearchNoteReqVO;
import com.jiahuiling.xiaojiashu.search.model.vo.SearchNoteRspVO;

/**

 * @description: 笔记搜索业务
 **/
public interface NoteService {

    /**
     * 搜索笔记
     * @param searchNoteReqVO
     * @return
     */
    PageResponse<SearchNoteRspVO> searchNote(SearchNoteReqVO searchNoteReqVO);
}

