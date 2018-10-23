package com.yulece.app.management.user.consumer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:CorsConfiguration
 * @Package com.yulece.app.management.zuul.configurer
 * @Description:全局跨域
 * @Date 创建时间 2018/10/5-10:11
 **/
@Component
@ComponentScan("com.yulece.app.management.comments.api")
public class CORSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
