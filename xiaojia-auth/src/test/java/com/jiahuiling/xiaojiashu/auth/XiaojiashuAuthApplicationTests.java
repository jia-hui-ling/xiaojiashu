package com.jiahuiling.xiaojiashu.auth;

import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserDO;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.UserDOMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class XiaojiashuAuthApplicationTests {
    @Resource
    private UserDOMapper userDOMapper;

    @Test
    void contextLoads() {
        UserDO userDO = UserDO.builder()
                .username("犬小哈")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        userDOMapper.insert(userDO);
    }

}
