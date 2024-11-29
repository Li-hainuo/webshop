package com.lihainuo.common.exception;

import com.lihainuo.common.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lihainuo
 * @description: 全局异常处理
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     * @return
     */
    @ExceptionHandler({ BusinessException.class })
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request, BusinessException e) {
        log.error("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getRespEnum().getErrorCode(), e.getRespEnum().getErrorMessage());
        log.error("系统异常", e);
        return Response.fail(e.getRespEnum());
    }

    @ExceptionHandler
    public Response<Object> handleException(Exception e) {
        log.error("系统异常", e);
        return Response.fail(e.getMessage());
    }

}