package com.jiahuiling.xiaojiashu.auth.controller;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 09:14
 * @Description TODO
 */
@RestController
public class TestController {
    @GetMapping("Test1")
    @ApiOperationLog(description = "哈哈哈")
    public Response<String> test1(){
        System.out.println(Response.fail("000"));
        return Response.success("你好");
    }
    @PostMapping("Test2")
    @ApiOperationLog(description = "哈哈哈")
    public Response<User> test2(@RequestBody User user){
        return Response.success(user);
    }
}
