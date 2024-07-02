package com.soukon.auth.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soukon.core.http.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;

@Slf4j
public class HttpUtils {

    public static  <T> void parseFromResponse(HttpServletResponse response, ApiResponse<T> apiResponse) {
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(apiResponse));
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Response转换失败", e);
        }
    }

}
