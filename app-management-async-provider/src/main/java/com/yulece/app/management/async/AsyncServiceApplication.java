package com.yulece.app.management.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ApplicationAsycService
 * @Package com.yulece.app.management.asyc
 * @Description:
 * @Date 2020-01-04 19:29
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class AsyncServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncServiceApplication.class,args);
    }
}
