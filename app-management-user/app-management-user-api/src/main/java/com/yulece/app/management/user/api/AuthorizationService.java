package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AuthorizationService
 * @Package com.yulece.app.management.user.api
 * @Description:
 * @Date 2019-03-30 19:41
 **/
@FeignClient(value = "app-management-user-provider",configuration = FeignHeaderInterceptor.class)
public interface AuthorizationService {

    @RequestMapping("/rest/checkIntercept")
    Set<String> checkIntercept();
}
