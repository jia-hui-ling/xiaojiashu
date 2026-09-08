package com.jiahuiling.xiaojiashu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.jiahuiling.xiaojiashu")
public class XiaojiashuAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuAuthApplication.class, args);
    }

}
