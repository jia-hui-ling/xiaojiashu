package com.jiahuiling.framework.biz.context.interceptor;

import com.jiahuiling.framework.biz.context.holder.LoginUserContextHolder;
import com.jiahuiling.framework.common.constant.GlobalConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 19:13
 * @Description Feign 请求拦截器
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取当前上下文中的用户 ID
        Long userId = LoginUserContextHolder.getUserId();
        // 若不为空，则添加到请求头中
        if (Objects.nonNull(userId)) {
            requestTemplate.header(GlobalConstants.USER_ID, String.valueOf(userId));
            log.info("########## feign 请求设置请求头 userId: {}", userId);
        }
    }
}
