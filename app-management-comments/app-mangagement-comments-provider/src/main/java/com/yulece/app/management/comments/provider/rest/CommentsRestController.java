package com.yulece.app.management.comments.provider.rest;

import com.yulece.app.management.comments.api.CommentsApiService;
import com.yulece.app.management.comments.api.entity.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsRestController implements CommentsApiService {

    @Autowired
    private CommentsApiService commentsApiService;

    @Override
    public void sendMail(@RequestBody EmailMessage emailMessage) {
        commentsApiService.sendMail(emailMessage);
    }
}
