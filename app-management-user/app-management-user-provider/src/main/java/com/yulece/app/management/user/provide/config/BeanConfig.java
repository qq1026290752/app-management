package com.yulece.app.management.user.provide.config;

import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.yulece.app.management.comments.api")
public class BeanConfig implements WebMvcConfigurer {

   @Autowired
   private LoginHandlerInterceptor loginHandlerInterceptor;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new SCryptPasswordEncoder();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**");
    }
}
