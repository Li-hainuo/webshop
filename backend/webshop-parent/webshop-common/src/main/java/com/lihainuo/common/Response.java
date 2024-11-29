package com.lihainuo.common;

import com.lihainuo.common.enums.RespEnum;
import lombok.Data;
/**
 * @author lihainuo
 * @description: 统一响应结果类
 */
@Data
public class Response<T> {
    // 是否成功，默认为 true
    private boolean success = true;
    // 响应消息
    private String message;
    // 异常码
    private Integer code;
    // 响应数据
    private T data;

    // =================================== 成功响应 ===================================
    public static <T> Response<T> success() {
        Response<T> response = new Response<>();
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    // =================================== 失败响应 ===================================
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(Integer errorCode, String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    // 需要实现全局异常管理
    public static <T> Response<T> fail(RespEnum respEnum) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(respEnum.getErrorCode());
        response.setMessage(respEnum.getErrorMessage());
        return response;
    }


}
