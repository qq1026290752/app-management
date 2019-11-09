package com.yulece.app.management.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ZuulOauthFilter
 * @Package com.yulece.app.management.gateway.filter
 * @Description:
 * @Date 2019-03-30 13:04
 **/
@Configuration
public class ZuulOauthFilter extends ZuulFilter {


    private final static Logger LOGGER = LoggerFactory.getLogger(ZuulOauthFilter.class);
    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Value("${auth.url}")
    private String authUrl;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 判断用户是否需要走验证方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        boolean isPermission = true;
        String requestURI = request.getRequestURI();
        //不需要走验证逻辑的URL
        String[] urls = StringUtils.split(authUrl, ",");
        for (String  url : urls) {
            if (antPathMatcher.match(url, requestURI)) {
                isPermission = false;
                LOGGER.info("地址:{},不需要进行访问",requestURI);
                break;
            }
        }
        return isPermission;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        try{
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            String authorization = request.getHeader("Authorization");
            String toke = StringUtils.substringAfter(authorization, "bearer ");
            Claims body = Jwts.parser().setSigningKey("yulece".getBytes("UTF-8")).parseClaimsJws(toke).getBody();
            Date expires_in = body.get("exp", Date.class);
            LOGGER.error("該用戶过期时间还剩:{},现在时间为:{}",expires_in,new Date());
            boolean isPermission = false;
            String requestURI = request.getRequestURI();
            //查询用户是否有权限访问路径
            Set<String> urls = new HashSet<>();
            urls.add("/me");
            for (String  url : urls) {
                if (antPathMatcher.match(url.trim(), requestURI)) {
                    isPermission = true;
                    LOGGER.info("地址:{},不需要进行访问", requestURI);
                    break;
                }
            }
            if(!isPermission){
                ctx.setSendZuulResponse(false);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().println("您没有访问该链接的权限,请稍后重试");
                return null;
            }
        }catch (ExpiredJwtException e){
            try{
                ctx.setSendZuulResponse(false);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("您的TOKEN已过期,请重新获取TOKEN");
                return null;
            }catch (IOException io){
                LOGGER.error("数据发送失败,失败原因是IOError:{}",io.getMessage());
            }
        }catch (Exception e){
            try {
                ctx.setSendZuulResponse(false);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println(e.getMessage());
                return null;
            }catch (IOException ioEx){
                LOGGER.error("数据发送失败,失败原因是IOError:{}",ioEx.getMessage());
            }
        }
        return null;
    }
}
