package com.yulece.app.management.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AppApiGatewayApplicationBootstrap
 * @Package com.yulece.app.management.gateway
 * @Description:
 * @Date 2019-03-24 11:06
 **/
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableScheduling
public class AppApiGatewayApplicationBootstrap {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppApiGatewayApplicationBootstrap.class);


    public static void main(String[] args) {
      SpringApplication.run(AppApiGatewayApplicationBootstrap.class, args);
    }

    private final ContextRefresher contextRefresher;
    private final Environment environment;

    public AppApiGatewayApplicationBootstrap(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    @Scheduled(fixedRate  = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh() {

        Set<String> updatedPropertyNames =
                contextRefresher.refresh();

        updatedPropertyNames.forEach( propertyName ->
                LOGGER.warn("[Thread :{}] 当前配置已更新，具体 Key：{} , Value : {}", Thread.currentThread().getName(), propertyName,
                        environment.getProperty(propertyName)));
    }

}
