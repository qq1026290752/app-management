package com.yulece.app.management.gateway;

import com.yulece.app.management.gateway.filter.ApplicationContextUtils;
import com.yulece.app.management.user.api.AuthorizationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

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
@EnableFeignClients(basePackageClasses = {AuthorizationService.class})
public class AppApiGatewayApplicationBootstrap {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AppApiGatewayApplicationBootstrap.class, args);
        ApplicationContextUtils applicationContextUtils = new ApplicationContextUtils();
        applicationContextUtils.setApplicationContext(applicationContext);
    }
}
