package com.soukon.auth.config;

import com.soukon.auth.filter.JWTAuthenticationFilter;
import com.soukon.auth.filter.JWTAuthorizationFilter;
import com.soukon.auth.handler.CustomAccessErrorHandler;
import com.soukon.auth.handler.LoginFailureHandler;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CustomAccessErrorHandler customAccessErrorHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager);
//        todo 后期可配置
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        jwtAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login", "/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(jwtAuthenticationFilter)
//                .addFilter(new JWTAuthorizationFilter(authenticationManager))
//                .sessionManagement().disable(); // Disable session management for stateless authentication
//        return http.build();
        return http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/login", "/register").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilter(new JWTAuthorizationFilter(authenticationManager))
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                            response.getWriter().write("Unauthorized: " + authException.getMessage());
//                        })
                        .accessDeniedHandler(customAccessErrorHandler))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}