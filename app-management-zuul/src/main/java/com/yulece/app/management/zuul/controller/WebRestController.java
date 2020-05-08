package com.yulece.app.management.zuul.controller;

import com.google.common.collect.Maps;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebRestController {

    private final ZuulProperties zuulProperties;

    public WebRestController(ZuulProperties zuulProperties) {
        this.zuulProperties = zuulProperties;
    }

    @GetMapping("/me")
    public String getCurrentUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        return user.getName();
    }
    @PostMapping("/hello")
    public String word(@RequestBody User user){
       return "success";
    }



}
