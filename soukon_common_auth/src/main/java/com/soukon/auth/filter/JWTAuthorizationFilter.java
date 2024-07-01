package com.soukon.auth.filter;

import com.soukon.auth.domain.UserDO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 登录之前获取token校验:如果有token、再去校验token的合法性:如果没有报错 则认为登录成功
 * 【token是根据SpringSecurity的Authentication生成的,相当于token就是SpringSecurity认证后的凭证】
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        UserDO userDO = new UserDO();
        userDO.setUsername("test");
        userDO.setPassword("test");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDO, null, new ArrayList<>()));
        filterChain.doFilter(request, response);
        //匿名地址直接访问
//        if(RedPigTools.contains(requestURI, Constant.annos)){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //获取JWT
//        String token = request.getHeader("Authorization");
//        log.info("接收到的token:{}",token);
//        if (token != null) {
//            try {
//                JwtUtils.tokenVerify(token);
//            }catch (Exception e){
//                response.setStatus(200);
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write(JSONUtil.toJsonStr(Result.error("非法token")));
//                return;
//            }
//        }
    }
}
