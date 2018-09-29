package com.yulece.app.management.comments.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ApplicationCommentsBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCommentsBootstrap.class,args);
    }
}
