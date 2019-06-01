package com.yulece.app.management.comments.api.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:FeignHeaderInterceptor
 * @Package com.yulece.app.management.comments.api.interceptor
 * @Description:
 * @Date 创建时间 2018/10/22-13:26
 **/
@Configuration
public class FeignHeaderInterceptor implements RequestInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(FeignHeaderInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
        Enumeration<String> bodyNames = request.getParameterNames();
        StringBuffer body =new StringBuffer();
        if (bodyNames != null) {
            body.append("{");
            while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                if(name.indexOf(".") != 1){
                    body.append("\"").append(name.substring(name.indexOf(".")+1)).append("\":").append(values);
                }else {
                    body.append(name).append("=").append(values).append("&");
                }
            }
            body.append("}");
        }

        if(body.length()!=0&&!body.toString().startsWith("{")) {
            if(body.toString().endsWith("&")){
                body.deleteCharAt(body.toString().length());
            }
            requestTemplate.body(body.toString());
            LOGGER.info("feign interceptor body:{}",body.toString());
        }

    }
}
