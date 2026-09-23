package com.jiahuiling.xiaojiashu.user.relation.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@MapperScan("com.jiahuiling.xiaojiashu.user.relation.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.jiahuiling.xiaojiashu")
public class XiaojiashuUserRelationBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuUserRelationBizApplication.class, args);
    }
}
