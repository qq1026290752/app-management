package com.yulece.app.management.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yulece.app.management.user.api.AuthorizationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String path = request.getServletPath();
        LOGGER.info("现在用户请求的地址是：{}",path);
        AuthorizationService bean = ApplicationContextUtils.getBean(AuthorizationService.class);
        return !bean.checkIntercept();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        try{
            String authorization = request.getHeader("Authorization");
            String toke = StringUtils.substringAfter(authorization, "bearer ");
            Claims body = Jwts.parser().setSigningKey("yulece".getBytes("UTF-8")).parseClaimsJws(toke).getBody();
            String user_name = body.get("user_name", String.class);
        }catch (Exception e){
            try{
                response.setHeader("Content-type","text/html;charset=UTF-8");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"请携带正确的TOKEN.");
            }catch (IOException io){
                LOGGER.error("数据发送失败,失败原因是IOError:{}",io.getMessage());
            }
        }
        return null;
    }
}
