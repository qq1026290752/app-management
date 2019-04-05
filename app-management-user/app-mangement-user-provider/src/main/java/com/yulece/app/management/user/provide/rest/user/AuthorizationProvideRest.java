package com.yulece.app.management.user.provide.rest.user;
import com.yulece.app.management.user.api.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AuthorizationRest
 * @Package com.yulece.app.management.user.provide.rest.user
 * @Description:
 * @Date 2019-03-30 20:21
 **/
@RestController
@RequestMapping("/rest")
public class AuthorizationProvideRest {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping("/checkIntercept")
    public Boolean checkIntercept(){
        return authorizationService.checkIntercept();
    }

}
