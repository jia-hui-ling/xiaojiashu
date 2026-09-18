package com.jiahuiling.xiaojiashu.note.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 贾慧玲
 * @Date 2026/9/14 10:08
 * @Description TODO
 */
@SpringBootApplication
@MapperScan("com.jiahuiling.xiaojiashu.note.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.jiahuiling.xiaojiashu")
public class XiaojiashuNoteBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuNoteBizApplication.class, args);
    }
}
