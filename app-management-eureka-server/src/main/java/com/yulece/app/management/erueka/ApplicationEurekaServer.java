package com.yulece.app.management.erueka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApplicationEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationEurekaServer.class,args);
    }
}
