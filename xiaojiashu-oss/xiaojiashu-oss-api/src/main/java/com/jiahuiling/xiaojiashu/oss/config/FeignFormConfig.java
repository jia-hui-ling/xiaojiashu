package com.jiahuiling.xiaojiashu.oss.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 贾慧玲
 * @Date 2026/9/8 19:03
 * @Description TODO
 */
@Configuration
public class FeignFormConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}


