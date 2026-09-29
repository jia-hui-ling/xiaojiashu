package com.jiahuiling.xiaojiashu.user.relation.biz.service;

import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.relation.biz.model.vo.FollowUserReqVO;
import com.jiahuiling.xiaojiashu.user.relation.biz.model.vo.UnfollowUserReqVO;

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
}
