package com.yulece.app.management.zuul.mobile.vlidate.sms;


import com.yulece.app.management.zuul.properties.ZuulProperties;
import com.yulece.app.management.zuul.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
/**
 * 默认发送验证码逻辑
 * @author w4837
 *
 */
@Slf4j 
public class AliyunSMSCodeSender implements SmsCodeSender {

	@Autowired
	private ZuulProperties zuulProperties;
	
	@Override
	@Async
	public SimpleResponse send(String mobile, String code) throws Exception {
		//log.info("向手机号:" + mobile +"发送了验证码,验证码是" + code);
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		//替换成你的AK
		final String accessKeyId = zuulProperties.getAliyunProperties().getAccessKeyId();//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = zuulProperties.getAliyunProperties().getAccessKeySecret();//你的accessKeySecret，参考本文档步骤2
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
		 request.setPhoneNumbers(mobile);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName(zuulProperties.getAliyunProperties().getSignName());
		 //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
		 request.setTemplateCode(zuulProperties.getAliyunProperties().getTemplateCode());
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		 String smsCode = String.format("{\"code\":\"%s\"}",code);
		 request.setTemplateParam(smsCode);
		 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 //request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			log.debug("短信发送成功,手机号为:{},验证码为:{}",mobile,code);
			return new SimpleResponse("短信已发送,5分钟之内有效");
		}
		log.error("短信发送失败,失败dode为:{},失败原因为:{}",sendSmsResponse.getCode(),sendSmsResponse.getMessage());
		return new SimpleResponse("短信发送失败,请稍后重试");
	}

}
