package com.jiahuiling.xiaojiashu.note.biz.consumer;

import com.jiahuiling.xiaojiashu.note.biz.constant.MQConstants;
import com.jiahuiling.xiaojiashu.note.biz.constant.RedisKeyConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(
        consumerGroup = "xiaojiashu_group",
        topic = MQConstants.TOPIC_DELAY_DELETE_NOTE_REDIS_CACHE// 消费的主题 Topic
)
public class DelayDeleteNoteRedisCacheConsumer implements RocketMQListener<String> {

    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public void onMessage(String message) {
        Long noteId = Long.valueOf(message);
        log.info("## 延迟消息消费成功, noteId: {}", noteId);

        // 删除 Redis 笔记缓存
        String noteDetailRedisKey = RedisKeyConstants.buildNoteDetailKey(noteId);
        redisTemplate.delete(noteDetailRedisKey);
    }
}
