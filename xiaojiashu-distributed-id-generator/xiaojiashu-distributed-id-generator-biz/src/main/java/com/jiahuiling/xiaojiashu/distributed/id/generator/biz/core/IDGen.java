package com.jiahuiling.xiaojiashu.distributed.id.generator.biz.core;

import com.jiahuiling.xiaojiashu.distributed.id.generator.biz.core.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
