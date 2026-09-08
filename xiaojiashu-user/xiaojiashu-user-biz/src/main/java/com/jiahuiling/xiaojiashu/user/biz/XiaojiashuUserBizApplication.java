package com.jiahuiling.xiaojiashu.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@MapperScan("com.jiahuiling.xiaojiashu.user.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.jiahuiling.xiaojiashu")
public class XiaojiashuUserBizApplication
{
    public static void main( String[] args ){
        SpringApplication.run(XiaojiashuUserBizApplication.class, args);
    }
}
