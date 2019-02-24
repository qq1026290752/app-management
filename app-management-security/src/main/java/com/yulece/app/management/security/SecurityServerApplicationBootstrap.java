package com.yulece.app.management.security;

import com.yulece.app.management.security.properties.SSOProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: SercurtyApplicationBootstrap
 * @Package com.yulece.app.management.security
 * @Description:
 * @Date 2019-02-24 12:57
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(SSOProperties.class)
public class SecurityServerApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplicationBootstrap.class,args);
    }
}
