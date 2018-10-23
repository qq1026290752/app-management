package com.yulece.app.management.zuul.service;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:UserServiceImpl
 * @Package com.yulece.app.management.zuul.service
 * @Description:
 * @Date 创建时间 2018/10/12-21:48
 **/
@Scope(value = "prototype")
@Service
public class UserServiceImpl implements UserService {


    @Override
    public Integer add() {
        return new Integer(RandomUtils.nextInt());
    }
}
