package com.soukon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.soukon.auth.config.WebSecurityConfig;
@Configuration
@Import(WebSecurityConfig.class)
public class SecurityConfig {
}
