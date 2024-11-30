package com.lihainuo.jwt.handler;

import com.lihainuo.common.Response;
import com.lihainuo.common.enums.RespEnum;
import com.lihainuo.jwt.exception.UsernameOrPasswordNullException;
import com.lihainuo.utils.ResultUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * @author lihainuo
 * @description: 登录失败处理器
 */
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, IOException {
        log.warn("AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            // 用户名或密码为空
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        } else if (exception instanceof BadCredentialsException) {
            // 用户名或密码错误
            ResultUtil.fail(response, Response.fail(RespEnum.USERNAME_OR_PWD_ERROR));
        }

        // 登录失败
        ResultUtil.fail(response, Response.fail(RespEnum.LOGIN_FAIL));
    }
}
