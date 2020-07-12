package com.yulece.app.management.zuul.mobile.vlidate;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.mobile.vlidate.sms.SmsCodeSender;
import com.yulece.app.management.zuul.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("validate")
public class ValidateController {
	
	
	/**
	 * 生成图像验证码
	 * 
	 * @param request
	 * @param response
	 */

	@Autowired
	private ValidateCodeGenerator smsValidateCodeGenerator;
	@Autowired
	private SmsCodeSender smsCodeSender;
	@Autowired
	private ValidateCodeRepository validateCodeRepository;
	@Autowired
	private  ObjectMapper objectMapper;

	
	/**
	 * 生成手机验证码
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@GetMapping("sms")
	public String createSmsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ValidateCode smsValidateCode = smsValidateCodeGenerator.createImageCode(request);
		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
		smsCodeSender.send(mobile,smsValidateCode.getCode());
		validateCodeRepository.save(new ServletWebRequest(request), smsValidateCode, ZuulAppConstant.SMS_SESSION_KEY);
		return objectMapper.writeValueAsString(new SimpleResponse("验证码发送成功"));
	}

 
 
}
