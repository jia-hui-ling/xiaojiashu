package com.jiahuiling.xiaojiashu.auth;

import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserDO;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.UserDOMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class XiaojiashuAuthApplicationTests {
    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    void contextLoads() {
        UserDO userDO = UserDO.builder()
                .id(9L)
                .username("test-user")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        userDOMapper.insert(userDO);
    }

    @Test
    void RedisTest() {
        redisTemplate.opsForValue().set("test-user","test-user");
    }

    @Test
    void testSubmit() {
        threadPoolTaskExecutor.submit(()->{
            log.info("异步线程");
        });
    }
}
