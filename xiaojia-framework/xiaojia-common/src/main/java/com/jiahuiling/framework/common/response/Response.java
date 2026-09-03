package com.jiahuiling.framework.common.response;

import com.jiahuiling.framework.common.exception.BaseExceptionInterFace;
import com.jiahuiling.framework.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 贾慧玲
 * @Date 2026/9/2 17:56
 * @Description TODO
 */
@Data
public class Response<T> implements Serializable {

    private boolean success = true;
    private T data;
    private String errorCode;
    private String message;

    public static <T> Response<T> success(){
        return new Response<>();
    }

    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String message){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> fail(String errorCode,String message){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(message);
        return response;
    }
    public static <T> Response<T> fail(BizException bizException){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getMessage());
        return response;
    }

    public static <T> Response<T> fail(BaseExceptionInterFace baseException){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(baseException.getErrorCode());
        response.setMessage(baseException.getErrorMsg());
        return response;
    }

}
