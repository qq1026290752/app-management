package com.yulece.app.management.zuul.mobile.vlidate.sms;
import com.yulece.app.management.zuul.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
/**
 * 默认发送验证码逻辑
 * @author w4837
 *
 */
@Slf4j 
public class DefaultSMSCodeSender implements SmsCodeSender {

	@Override
	public SimpleResponse send(String mobile, String code) throws Exception {
		 	log.debug("短信发送成功,手机号为:{},验证码为:{}",mobile,code);
			return new SimpleResponse("短信已发送,5分钟之内有效");
	}

}
