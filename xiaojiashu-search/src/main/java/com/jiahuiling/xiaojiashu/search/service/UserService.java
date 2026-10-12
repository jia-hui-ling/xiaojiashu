package com.jiahuiling.xiaojiashu.search.service;

import com.jiahuiling.framework.common.response.PageResponse;
import com.jiahuiling.xiaojiashu.search.model.vo.SearchUserReqVO;
import com.jiahuiling.xiaojiashu.search.model.vo.SearchUserRspVO;

/**

 * @description: 用户搜索业务
 **/
public interface UserService {

    /**
     * 搜索用户
     * @param searchUserReqVO
     * @return
     */
    PageResponse<SearchUserRspVO> searchUser(SearchUserReqVO searchUserReqVO);
}

