package com.jiahuiling.framework.biz.operationlog.config;

import com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLogAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 10:11
 * @Description TODO
 */

@AutoConfiguration
public class ApiOperationLogAutoConfiguration {
    @Bean
    public ApiOperationLogAspect apiOperationLog(){
        return new ApiOperationLogAspect();
    }
}
