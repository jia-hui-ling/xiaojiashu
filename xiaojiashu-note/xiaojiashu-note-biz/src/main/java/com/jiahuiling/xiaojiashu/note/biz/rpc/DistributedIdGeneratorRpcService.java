package com.jiahuiling.xiaojiashu.note.biz.rpc;

import com.jiahuiling.xiaojiashu.distributed.generator.api.DistributedIdGeneratorFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DistributedIdGeneratorRpcService {
    @Resource
    private DistributedIdGeneratorFeignApi distributedIdGeneratorFeignApi;
    /**
     * 生成雪花算法 ID
     *
     * @return
     */
    public String getSnowflakeId(){
        return distributedIdGeneratorFeignApi.getSnowflakeId("jiahuiling");
    }
}
