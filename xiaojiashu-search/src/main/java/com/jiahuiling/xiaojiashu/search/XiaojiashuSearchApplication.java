package com.jiahuiling.xiaojiashu.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.jiahuiling.xiaojiashu.search.domain.mapper")
public class XiaojiashuSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuSearchApplication.class);
    }
}
