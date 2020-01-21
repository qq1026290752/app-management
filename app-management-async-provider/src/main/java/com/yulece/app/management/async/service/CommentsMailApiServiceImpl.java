package com.yulece.app.management.async.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yulece.app.management.comments.api.CommentsMailApiService;
import com.yulece.app.management.comments.api.entity.EmailMessage;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: CommentsMailApiServiceImpl
 * @Package com.yulece.app.management.async.service
 * @Description:
 * @Date 2020-01-04 20:35
 **/
@Service
public class CommentsMailApiServiceImpl implements CommentsMailApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentsMailApiServiceImpl.class);
    @Value("${ALIYUN_MAIL_ACCESSKEY}")
    private String aliyunMailAccesskey;
    @Value("${ALIYUN_MAIL_ACCESSSECRET}")
    private String aliyunMailAccessSecret;
    @Value("${ALIYUN_MAIL_FROMALIAS}")
    private String aliyunMailFromAlias;
    @Value("${ALIYUN_MAIL_HOST}")
    private String aliyunMailHOST;
    @Override
    public ResultVo sendMail(EmailMessage emailMessage) {
        BeanValidator.check(emailMessage);
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunMailAccesskey, aliyunMailAccessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            request.setAccountName(aliyunMailHOST);
            request.setFromAlias(aliyunMailFromAlias);
            request.setAddressType(1);
            request.setReplyToAddress(true);
            request.setToAddress(emailMessage.getEmail());
            request.setSubject(emailMessage.getTitle());
            request.setHtmlBody(emailMessage.getContext());
            request.setMethod(MethodType.POST);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
            LOGGER.info("邮件发送成功,发送邮件地址为:{},获取到的消息是:{}",emailMessage.getEmail(),httpResponse.getRequestId());
        } catch (ClientException e) {
            LOGGER.error("邮件发送失败,发送邮件地址为:{},发送异常码为:{},异常原因为:{}",emailMessage.getEmail(),e.getErrCode(),e.getErrMsg());
            throw new AppException(700,e.getMessage());
        }
        return ResultVo.createSuccessResult();
    }
}
