package com.jiahuiling.framework.common.xiaojiashu.auth.controller;

import com.jiahuiling.framework.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 09:14
 * @Description TODO
 */
@RestController
public class TestController {
    @GetMapping("Test")
    public Response<String> test(){
        System.out.println(Response.fail("000"));
        return Response.success("你好");
    }
}
