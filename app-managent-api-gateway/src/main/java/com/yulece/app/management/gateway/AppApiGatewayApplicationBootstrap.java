package com.yulece.app.management.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AppApiGatewayApplicationBootstrap
 * @Package com.yulece.app.management.gateway
 * @Description:
 * @Date 2019-03-24 11:06
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AppApiGatewayApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AppApiGatewayApplicationBootstrap.class,args);
    }
}
