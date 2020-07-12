package com.yulece.app.management.zuul.mobile.vlidate.sms;


import javax.servlet.ServletRequest;

import com.yulece.app.management.zuul.mobile.vlidate.ValidateCode;
import com.yulece.app.management.zuul.mobile.vlidate.ValidateCodeGenerator;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
	
	@Autowired
	private ZuulProperties zuulProperties;
	
	@Override
	public ValidateCode createImageCode(ServletRequest request) {
		String code = RandomStringUtils.randomNumeric(zuulProperties.getSmsCodeProperties().getCodeLen());
        log.info("生成的验证码为:" + code);
        return new ValidateCode(code,zuulProperties.getSmsCodeProperties().getExpireIn());
    }
}
