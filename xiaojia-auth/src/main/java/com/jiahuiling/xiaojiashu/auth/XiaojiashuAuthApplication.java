package com.jiahuiling.xiaojiashu.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiahuiling.xiaojiashu.auth.domain.mapper")
public class XiaojiashuAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuAuthApplication.class, args);
    }

}
