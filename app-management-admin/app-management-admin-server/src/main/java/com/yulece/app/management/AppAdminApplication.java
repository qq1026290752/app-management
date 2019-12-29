package com.yulece.app.management;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yulece.app.management.commons.utils.annotation.EnableExceptionUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AppAdminApplication
 * @Package com.yulece.app.management
 * @Description:
 * @Date 2019-11-09 15:00
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yulece.app.management.pms.repository")
@EnableExceptionUtils
@EnableTransactionManagement
public class AppAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppAdminApplication.class,args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
         return new PaginationInterceptor();
    }
}
