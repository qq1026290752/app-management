package com.yulece.app.management.zuul.authorization.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:DefaultRbacService
 * @Package com.yulece.app.management.zuul.authorization.service
 * @Description:
 * @Date 创建时间 2018/9/30-18:58
 **/
@Component("defaultZuulAuthorizationService")
public class DefaultZuulAuthorizationService implements ZuulAuthorizationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultZuulAuthorizationService.class);

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        boolean isPermission = false;
        String requestURI = request.getRequestURI();
        if(StringUtils.isEmpty(requestURI)){
            return false;
        }
        //匿名用户不能经过授权
        if(authentication!=null&&!authentication.getPrincipal().equals("anonymousUser")&&
                authentication.isAuthenticated()) {

            String username = (String) authentication.getPrincipal();
            //读取用户所有的Url,可以通过用户服务拿到当前用户服务拿到该用户的能访问的地址
            Set<String> urls = new HashSet<>();
            urls.add("/app/**");
            urls.add("/me");
            urls.add("/hello");
            for (String  url : urls) {
                if (antPathMatcher.match(url, requestURI)) {
                    isPermission = true;
                    LOGGER.info("用户[{}]鉴权,鉴权地址为:{}.",username,requestURI);
                    break;
                }
            }
            return isPermission;
        }
        return false;
    }
}
