package com.jiahuiling.framework.biz.operationlog.aspect;

import com.jiahuiling.framework.jackson.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 09:45
 * @Description TODO
 */
@Slf4j
@Aspect
public class ApiOperationLogAspect {
    @Pointcut("@annotation(com.jiahuiling.framework.biz.operationlog.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    /**
     * 环绕
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        String className= joinPoint.getTarget().getClass().getSimpleName();
        String methodName= joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        String argsJsonStr = Arrays.stream(args).map(JsonUtils::toJsonString).collect(Collectors.joining(","));

        String description=getApiOperationLogDescription(joinPoint);

        log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                description, argsJsonStr, className, methodName);

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - startTime;

        log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                description, executionTime,JsonUtils.toJsonString(result));

        return result;
    }

    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();

        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        return apiOperationLog.description();
    }

}
