package com.yulece.app.management.user.consumer;

import com.yulece.app.management.user.api.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses
        = {AdminUserService.class, AdminDeptService.class, AdminTreeService.class,
        AdminAclService.class, AdminAclModuleService.class,AdminRoleService.class,
        AdminRoleUserService.class})
public class ApplicationUserConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationUserConsumerBootstrap.class,args);
    }
}
