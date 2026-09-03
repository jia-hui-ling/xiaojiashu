package com.jiahuiling.framework.biz.operationlog.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * API功能描述
     *
     * {@code @retrun}
     */
    String description() default "";
}
