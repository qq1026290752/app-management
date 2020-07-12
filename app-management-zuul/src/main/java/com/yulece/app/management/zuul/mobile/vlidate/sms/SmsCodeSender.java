package com.yulece.app.management.zuul.mobile.vlidate.sms;


import com.yulece.app.management.zuul.support.SimpleResponse;

public interface SmsCodeSender {
	/**
	 * 发送验证码
	 * @param mobile
	 * @param code
	 */
	SimpleResponse send(String mobile, String code) throws Exception;
}
