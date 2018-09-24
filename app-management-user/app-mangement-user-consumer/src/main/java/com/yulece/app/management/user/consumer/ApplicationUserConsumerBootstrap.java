package com.yulece.app.management.user.consumer;

import com.yulece.app.management.user.api.AdminUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {AdminUserService.class})
public class ApplicationUserConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationUserConsumerBootstrap.class,args);
    }
}
