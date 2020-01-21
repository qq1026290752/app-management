package com.yulece.app.management.async.controller;

import com.yulece.app.management.comments.api.CommentsMailApiService;
import com.yulece.app.management.comments.api.entity.EmailMessage;
import com.yulece.app.management.commons.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ApiController
 * @Package com.yulece.app.management.async.controller
 * @Description:
 * @Date 2020-01-04 20:10
 **/
@RestController
@RequestMapping("/async")
public class MailApiController {


    private final CommentsMailApiService commentsMailApiService;
    @Autowired
    public MailApiController(CommentsMailApiService commentsMailApiService) {
        this.commentsMailApiService = commentsMailApiService;
    }

    @PostMapping("/sendMail")
    public ResultVo sendMail(@RequestBody EmailMessage emailMessage){
        return commentsMailApiService.sendMail(emailMessage);
    }


}
