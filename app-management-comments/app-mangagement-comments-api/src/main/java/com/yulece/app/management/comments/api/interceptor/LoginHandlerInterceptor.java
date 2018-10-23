package com.yulece.app.management.comments.api.interceptor;

import com.yulece.app.management.comments.api.utils.IpUtils;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:LoginHandlerInterceptor
 * @Package com.yulece.app.management.comments.api
 * @Description:
 * @Date 创建时间 2018/10/21-22:53
 **/
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    public static ThreadLocal<Map> local = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info(">>>>>>>>>>>>>服务被访问了<<<<<<<<<<<<<<<<<");
        //获取到Authorization 解析为JWTtoken
        String authorization = request.getHeader("Authorization");
        String toke = StringUtils.substringAfter(authorization, "bearer ");

        Map<String, Object> operateMap =
                Jwts.parser().setSigningKey("yulece".getBytes("UTF-8")).parseClaimsJws(toke).getBody();
        String operateIp = IpUtils.getIpFromRequest(request);
        operateMap.put("operate_ip",operateIp);
        local.set(operateMap);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info(">>>>>>>>>>>>>访问结束,删除local里面的数据<<<<<<<<<<<<<<<<<");
        local.remove();
    }
}
