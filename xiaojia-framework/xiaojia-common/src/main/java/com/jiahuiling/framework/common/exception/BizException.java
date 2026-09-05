package com.jiahuiling.framework.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author 贾慧玲
 * @Date 2026/9/2 17:56
 * @Description TODO
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
