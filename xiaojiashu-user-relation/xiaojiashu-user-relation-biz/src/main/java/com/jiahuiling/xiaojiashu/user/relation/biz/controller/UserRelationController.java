package com.jiahuiling.xiaojiashu.user.relation.biz.controller;

import com.jiahuiling.xiaojiashu.user.relation.biz.service.RelationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/relation")
public class UserRelationController {
    @Resource
    private RelationService relationService;
}
