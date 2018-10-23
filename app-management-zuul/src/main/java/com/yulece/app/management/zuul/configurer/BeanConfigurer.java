package com.yulece.app.management.zuul.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor")
public class BeanConfigurer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetails() {
        return new InMemoryClientDetailsService();
    }
}
