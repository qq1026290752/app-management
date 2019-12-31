package com.yulece.app.management.comments.api;

import com.yulece.app.management.comments.api.entity.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("app-async-provider")
public interface CommentsApiService {

    @PostMapping("/sendMail")
    void sendMail(EmailMessage emailMessage);
}
