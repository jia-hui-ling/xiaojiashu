package com.jiahuiling.xiaojiashu.user.relation.biz.service;

import com.jiahuiling.framework.common.response.PageResponse;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.relation.biz.model.vo.*;

/**
 * @description: 关注业务
 **/
public interface RelationService  {
    /**
     * 关注用户
     *
     * @param followUserReqVO
     * @return
     */
    Response<?> follow(FollowUserReqVO followUserReqVO);

    /**
     * 取关用户
     *
     * @param unfollowUserReqVO
     * @return
     */
    Response<?> unfollow(UnfollowUserReqVO unfollowUserReqVO);

    /**
     * 查询关注列表
     * @param findFollowingListReqVO
     * @return
     */
    PageResponse<FindFollowingUserRspVO> findFollowingList(FindFollowingListReqVO findFollowingListReqVO);

    /**
     * 查询粉丝列表
     * @param findFansListReqVO
     * @return
     */
    PageResponse<FindFansUserRspVO> findFansList(FindFansListReqVO findFansListReqVO);
}
