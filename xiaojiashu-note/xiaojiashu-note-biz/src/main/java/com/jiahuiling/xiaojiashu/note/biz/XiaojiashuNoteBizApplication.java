package com.jiahuiling.xiaojiashu.note.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 贾慧玲
 * @Date 2026/9/14 10:08
 * @Description TODO
 */
@SpringBootApplication
@MapperScan("com.jiahuiling.xiaojiashu.note.biz.domain.mapper")
public class XiaojiashuNoteBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(XiaojiashuNoteBizApplication.class, args);
    }
}
