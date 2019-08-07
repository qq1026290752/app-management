package com.yulece.app.management.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${auth.url}")
    private String authUrl;

    @GetMapping("tokens")
    public String token(){
        return authUrl;
    }

}
