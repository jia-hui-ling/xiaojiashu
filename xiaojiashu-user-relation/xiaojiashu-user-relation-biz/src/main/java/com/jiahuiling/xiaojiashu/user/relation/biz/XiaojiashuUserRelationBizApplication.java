package com.jiahuiling.xiaojiashu.user.relation.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiahuiling.xiaojiashu.user.relation.biz.domain.mapper")
public class XiaojiashuUserRelationBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuUserRelationBizApplication.class, args);
    }
}
