package com.yulece.app.management.user.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: IndexController
 * @Package com.yulece.app.management.user.consumer.controller
 * @Description:
 * @Date 2019-02-23 14:51
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("router")
    public String router(){
        return "router";
    }
}
