package com.jiahuiling.xiaojiashu.user.relation.biz.controller;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.relation.biz.model.vo.FindFollowingListReqVO;
import com.jiahuiling.xiaojiashu.user.relation.biz.model.vo.FollowUserReqVO;
import com.jiahuiling.xiaojiashu.user.relation.biz.model.vo.UnfollowUserReqVO;
import com.jiahuiling.xiaojiashu.user.relation.biz.service.RelationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
public class UserRelationController {
    @Resource
    private RelationService relationService;

    @PostMapping("/follow")
    @ApiOperationLog(description = "关注用户")
    public Response<?> follow(@Validated @RequestBody FollowUserReqVO followUserReqVO) {
        return relationService.follow(followUserReqVO);
    }

    @PostMapping("/unfollow")
    @ApiOperationLog(description = "取关用户")
    public Response<?> unfollow(@Validated @RequestBody UnfollowUserReqVO unfollowUserReqVO) {
        return relationService.unfollow(unfollowUserReqVO);
    }

    @PostMapping("/following/list")
    @ApiOperationLog(description = "关注列表")
    public Response<?> findFollowingList(@Validated @RequestBody FindFollowingListReqVO findFollowingListReqVO){
        return relationService.findFollowingList(findFollowingListReqVO);
    }
}
