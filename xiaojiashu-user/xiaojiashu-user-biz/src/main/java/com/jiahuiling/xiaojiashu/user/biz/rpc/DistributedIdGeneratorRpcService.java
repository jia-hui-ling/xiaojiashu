package com.jiahuiling.xiaojiashu.user.biz.rpc;

import com.jiahuiling.xiaojiashu.distributed.generator.api.DistributedIdGeneratorFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author 贾慧玲
 * @Date 2026/9/11 11:17
 * @Description 分布式 ID 生成服务
 */
@Component
public class DistributedIdGeneratorRpcService {
    @Resource
    private DistributedIdGeneratorFeignApi distributedIdGeneratorFeignApi;

    /**
     * Leaf 号段模式：小贾书 ID 业务标识
     */
    private static final String BIZ_TAG_XIAOJIASHU_ID = "leaf-segment-xiaojiashu-id";

    /**
     * 调用分布式 ID 生成服务生成小贾书 ID
     *
     * @return
     */
    public String getXiaojiashuId() {
        return distributedIdGeneratorFeignApi.getSegmentId(BIZ_TAG_XIAOJIASHU_ID);
    }
}
