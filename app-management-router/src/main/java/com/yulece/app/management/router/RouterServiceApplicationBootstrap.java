package com.yulece.app.management.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: RouterServiceApplicationBootstrap
 * @Package com.yulece.app.management.router
 * @Description:
 * @Date 2019-02-23 15:01
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class RouterServiceApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(RouterServiceApplicationBootstrap.class,args);
    }
}
