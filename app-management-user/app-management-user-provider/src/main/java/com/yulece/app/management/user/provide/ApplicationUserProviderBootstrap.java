package com.yulece.app.management.user.provide;

import com.yulece.app.management.comments.api.CommentsApiService;
import com.yulece.app.management.user.provide.interceptor.PageInterceptor;
import com.yulece.app.management.user.provide.properties.AppAdminProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages= "com.yulece.app.management.user.provide.repositories")
@EnableFeignClients(basePackageClasses = {CommentsApiService.class})
@EnableConfigurationProperties(AppAdminProperties.class)
public class ApplicationUserProviderBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationUserProviderBootstrap.class,args);
    }

    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor("mysql");
    }
}
