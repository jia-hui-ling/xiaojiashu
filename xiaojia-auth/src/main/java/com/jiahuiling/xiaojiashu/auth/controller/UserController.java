package com.jiahuiling.xiaojiashu.auth.controller;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UpdatePasswordReqVO;
import com.jiahuiling.xiaojiashu.auth.model.VO.user.UserLoginReqVO;
import com.jiahuiling.xiaojiashu.auth.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 18:47
 * @Description TODO
 */

@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperationLog(description = "用户登录注册")
    public Response<String> loginAndRegister(@Validated @RequestBody UserLoginReqVO userLoginReqVO){
        return userService.LoginAndRegister(userLoginReqVO);
    }

    @PostMapping("/logout")
    @ApiOperationLog(description = "账号登出")
    public Response<?> logout() {
        return userService.logout();
    }

    @PostMapping("/password/update")
    @ApiOperationLog(description = "密码修改")
    public Response<?> updatePassword(@Validated @RequestBody UpdatePasswordReqVO updatePasswordReqVO){
        return userService.updatePassword(updatePasswordReqVO);
    }
}
