package com.yulece.app.management.zuul.mobile.vlidate;

import com.yulece.app.management.zuul.mobile.vlidate.sms.AliyunSMSCodeSender;
import com.yulece.app.management.zuul.mobile.vlidate.sms.DefaultSMSCodeSender;
import com.yulece.app.management.zuul.mobile.vlidate.sms.SmsCodeSender;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class ValidateCodeBeaConfig {

	
	@Autowired
	private ZuulProperties zuulProperties;

	/**
	 * 短信验证码发送接口
	 * @return
	 */
	
	@Bean
	//判断阿里云短信接口是否存在
	@ConditionalOnProperty(prefix = "app.zuul.aliyun-properties",name = "accessKeyId")
	public SmsCodeSender smsCodeSender() {
		log.info("开始初始化阿里云短信配置");
		SmsCodeSender smsCodeSender = new AliyunSMSCodeSender();
		return smsCodeSender;
	}
	@Bean 
	@ConditionalOnMissingBean(name = "smsCodeSender")
	public SmsCodeSender aliyunSmsCodeSender() {
		log.info("未找到阿里云配置请设置阿里云短信配置");
		SmsCodeSender smsCodeSender = new DefaultSMSCodeSender();
		return smsCodeSender;
	}
}
