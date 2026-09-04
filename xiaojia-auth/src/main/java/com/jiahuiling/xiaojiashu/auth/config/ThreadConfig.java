package com.jiahuiling.xiaojiashu.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 12:59
 * @Description TODO
 */
@Configuration
public class ThreadConfig {
    @Bean(name="taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);//核心线程数
        executor.setMaxPoolSize(50);//最大线程数
        executor.setQueueCapacity(200);//队列容量
        executor.setKeepAliveSeconds(30);//线程活跃时间
        executor.setThreadNamePrefix("AuthExecutor-");//线程名前缀

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//拒接策略
        executor.setWaitForTasksToCompleteOnShutdown(true);//等待任务结束再关闭线程池
        executor.setAwaitTerminationSeconds(60);//设置等待时间

        executor.initialize();
        return executor;
    }
}
