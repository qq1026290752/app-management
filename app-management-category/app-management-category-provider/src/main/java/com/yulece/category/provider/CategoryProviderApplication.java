package com.yulece.category.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: CategoryProviderApplication
 * @Package com.yulece.category.provider
 * @Description:
 * @Date 2019-08-07 18:04
 **/
@SpringCloudApplication
@EnableEurekaClient
@EnableJpaRepositories
public class CategoryProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryProviderApplication.class,args);
    }
}
