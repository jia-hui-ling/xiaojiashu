package com.jiahuiling.xiaojiashu.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.exception.BizException;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.enums.ResponseCodeEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 09:14
 * @Description TODO
 */
@RestController
public class TestController {
    @GetMapping("Test1")
    @ApiOperationLog(description = "哈哈哈")
    public Response<String> test1() {
        System.out.println(Response.fail("000"));
        int i = 1 / 0;
        return Response.success();
    }

    @PostMapping("Test2")
    @ApiOperationLog(description = "哈哈哈")
    public Response<User> test2(@RequestBody @Validated User user) {
        return Response.success(user);
    }

    @RequestMapping("/user/doLogin")
    public String doLogin(String username, String password) {
        if("jia".equals(username) && "123456".equals(password)){
            StpUtil.login(10001);
            return "success";
        }
        return "fail";
    }

    @RequestMapping("/user/isLogin")
    public String isLogin() {
        return StpUtil.isLogin()?"success":"fail";
    }
}
