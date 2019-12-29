package com.yulece.app.management.commons.utils.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulece.app.management.commons.utils.config.SpringExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ExceptionConfig
 * @Package com.yulece.app.management.commons.utils.config
 * @Description:
 * @Date 2019-11-17 12:24
 **/
@Configuration
public class ExceptionConfig {
    private final ObjectMapper objectMapper;

    public ExceptionConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public SpringExceptionResolver springExceptionResolver(){
        return new SpringExceptionResolver(objectMapper);
    }
}
