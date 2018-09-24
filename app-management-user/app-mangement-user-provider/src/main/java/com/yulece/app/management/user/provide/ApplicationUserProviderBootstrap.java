package com.yulece.app.management.user.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages= "com.yulece.app.management.user.provide.repositories")
public class ApplicationUserProviderBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationUserProviderBootstrap.class,args);
    }
}
