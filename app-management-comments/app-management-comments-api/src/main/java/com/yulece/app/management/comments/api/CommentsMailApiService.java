package com.yulece.app.management.comments.api;

import com.yulece.app.management.comments.api.entity.EmailMessage;
import com.yulece.app.management.commons.utils.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient("app-async-provider")
public interface CommentsMailApiService {

    @PostMapping("/async/sendMail")
    ResultVo sendMail(EmailMessage emailMessage);
}
