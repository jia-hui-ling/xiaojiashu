package com.jiahuiling.xiaojiashu.auth;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDateTime;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 13:50
 * @Description TODO
 */
@SpringBootTest
@Slf4j
public class RedisTests {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    void RedisTest() {
        redisTemplate.opsForValue().set("test-user", "test-user");
    }
}