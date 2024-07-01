package com.soukon.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soukon.core.http.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *  认证失败处理 - 前后端分离情况下返回json数据格式
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        errResp(response, e.getMessage());
    }

    private void errResp(HttpServletResponse response, String msg) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(ApiResponse.error(msg)));
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("登录失败处理错误", e);
        }


    }

}
