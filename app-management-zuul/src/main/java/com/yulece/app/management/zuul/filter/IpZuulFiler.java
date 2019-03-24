package com.yulece.app.management.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yulece.app.management.commons.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: IpZuulFiler
 * @Package com.yulece.app.management.zuul.filter
 * @Description:
 * @Date 2019-03-23 21:58
 **/
public class IpZuulFiler extends ZuulFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(IpZuulFiler.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info("--->>> IpZuulFiler {},{}", request.getMethod(), request.getRequestURL().toString());
        String ip = IPUtils.getIp(request);
        LOGGER.info("IPUtils.getIp获取到的用户ip为:{}",ip);
        return null;
    }
}
