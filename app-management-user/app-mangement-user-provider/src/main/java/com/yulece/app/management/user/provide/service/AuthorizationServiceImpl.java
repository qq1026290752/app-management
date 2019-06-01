package com.yulece.app.management.user.provide.service;

import com.google.common.collect.Sets;
import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.user.api.AuthorizationService;
import com.yulece.app.management.user.provide.properties.AppAdminProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AuthorizationServiceImpl
 * @Package com.yulece.app.management.user.provide
 * @Description:
 * @Date 2019-03-30 19:54
 **/
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    
    @Autowired
    private AppAdminProperties appAdminProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Override
    public Set<String> checkIntercept() {
        //当前用户
        String currentUser = LoginHandlerInterceptor.getCurrentUser();

        HashSet<String> authorizationSets = Sets.newHashSet("/app/**", "/order/**");
        //查詢當前用戶
        return authorizationSets;
    }
}
