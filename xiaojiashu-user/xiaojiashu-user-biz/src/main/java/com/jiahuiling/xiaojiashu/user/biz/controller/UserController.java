package com.jiahuiling.xiaojiashu.user.biz.controller;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog;
import com.jiahuiling.framework.common.response.Response;
import com.jiahuiling.xiaojiashu.user.biz.model.vo.UpdateUserInfoReqVO;
import com.jiahuiling.xiaojiashu.user.biz.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 10:50
 * @Description TODO
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户信息修改
     *
     * @param updateUserInfoReqVO
     * @return
     */
    @PostMapping(value = "/update",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    不添加切面日志注解 @ApiOperationLog，此接口包含文件流上传，Jackson 序列化会有问题！！！
    public Response<?> updateUser(@Valid UpdateUserInfoReqVO updateUserInfoReqVO){
        return userService.updateUserInfo(updateUserInfoReqVO);
    }
}
