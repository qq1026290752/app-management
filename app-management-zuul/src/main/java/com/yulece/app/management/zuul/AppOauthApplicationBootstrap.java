package com.yulece.app.management.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AppOauthApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AppOauthApplicationBootstrap.class,args);
    }
}

