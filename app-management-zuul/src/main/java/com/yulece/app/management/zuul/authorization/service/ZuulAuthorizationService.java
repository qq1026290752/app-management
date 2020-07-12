package com.yulece.app.management.zuul.authorization.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:ZuulAuthorizationService
 * @Package com.yulece.app.management.zuul.authorization.service
 * @Description:
 * @Date 创建时间 2018/9/30-18:23
 **/
public interface ZuulAuthorizationService {

    /**
     * 验证用户是否有权登录
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request , Authentication authentication);
}
