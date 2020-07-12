package com.yulece.app.management.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ZuulErrorFilter
 * @Package com.yulece.app.management.gateway.filter
 * @Description: error 过滤器
 * @Date 2019-06-01 19:29
 **/
@Component
public class ZuulErrorFilter extends ZuulFilter {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ZuulErrorFilter.class);
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 9;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getResponseStatusCode() == 500;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String responseBody = ctx.getResponseBody();
        LOGGER.error("这里是ERROR过滤器,拦截到的内容是:{}",responseBody);
        return null;
    }
}
