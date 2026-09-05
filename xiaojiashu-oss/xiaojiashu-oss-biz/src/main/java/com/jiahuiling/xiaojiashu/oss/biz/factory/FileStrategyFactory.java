package com.jiahuiling.xiaojiashu.oss.biz.factory;

import com.jiahuiling.xiaojiashu.oss.biz.strategy.FileStrategy;
import com.jiahuiling.xiaojiashu.oss.biz.strategy.Impl.AliyunOSSFileStrategy;
import com.jiahuiling.xiaojiashu.oss.biz.strategy.Impl.MinioFileStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 贾慧玲
 * @Date 2026/9/5 18:55
 * @Description TODO
 */
@Configuration
@RefreshScope
public class FileStrategyFactory {

    @Value("${storage.type}")
    private String strategyType;

    @Bean
    public FileStrategy getFileStrategy() {
        if (StringUtils.equals(strategyType, "minio")) {
            return new MinioFileStrategy();
        } else if (StringUtils.equals(strategyType, "aliyun")) {
            return new AliyunOSSFileStrategy();
        }

        throw new IllegalArgumentException("不可用的存储类型");
    }

}
