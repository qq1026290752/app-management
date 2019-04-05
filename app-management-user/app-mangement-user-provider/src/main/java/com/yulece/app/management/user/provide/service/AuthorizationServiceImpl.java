package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.user.api.AuthorizationService;
import com.yulece.app.management.user.provide.properties.AppAdminProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Boolean checkIntercept() {
        //获取
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Boolean isPermission = false;
        String notInterceptUrl = appAdminProperties.getNotInterceptUrl();
        String[] split = notInterceptUrl.split(",");
        for (String uri : split) {
            if (antPathMatcher.match(uri, request.getRequestURI())) {
                isPermission = true;
                LOGGER.info("当前访问URL:{}无需经过权限认真系统.",request.getRequestURI());
                break;
            }
        }
        return isPermission;
    }
}
