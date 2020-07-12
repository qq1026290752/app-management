package com.yulece.app.management.zuul.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:UserServiceTest
 * @Package com.yulece.app.management.zuul.service
 * @Description:
 * @Date 创建时间 2018/10/12-22:02
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void add() {
        LOGGER.info("userService:{}",userService);
        LOGGER.info("userService:{}",userService);
        LOGGER.info("userService:{}",userService);
    }
}