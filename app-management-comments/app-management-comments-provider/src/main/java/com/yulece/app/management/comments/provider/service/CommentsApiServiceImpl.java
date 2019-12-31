package com.yulece.app.management.comments.provider.service;

import com.yulece.app.management.comments.api.CommentsApiService;
import com.yulece.app.management.comments.api.entity.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CommentsApiServiceImpl implements CommentsApiService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendMail(@RequestBody EmailMessage emailMessage) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject(emailMessage.getTitle());
        mailMessage.setTo(emailMessage.getEmail());
        mailMessage.setText(emailMessage.getContext());
        javaMailSender.send(mailMessage);
        LOGGER.info("[用户注册]:邮件发送成功,发送邮箱为{}:", emailMessage.getEmail());
    }
}
