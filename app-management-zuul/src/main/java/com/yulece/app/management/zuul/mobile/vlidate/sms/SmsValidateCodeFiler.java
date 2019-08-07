package com.yulece.app.management.zuul.mobile.vlidate.sms;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yulece.app.management.commons.utils.enums.SecurityEnum;
import com.yulece.app.management.commons.utils.exception.SMSException;
import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.mobile.vlidate.ValidateCode;
import com.yulece.app.management.zuul.mobile.vlidate.ValidateCodeRepository;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.Data;

@Data
@Log4j2
@EqualsAndHashCode(callSuper = true)
public class SmsValidateCodeFiler extends OncePerRequestFilter implements InitializingBean {

	private ValidateCodeRepository validateCodeRepository;

	public SmsValidateCodeFiler(ValidateCodeRepository validateCodeRepository) {
		this.validateCodeRepository = validateCodeRepository;
	}

	private AuthenticationFailureHandler appAuthenticationFailureHandler;
	//存放所有验证码路径
	private Set<String> urls = new HashSet<>();

	private ZuulProperties zuulProperties;
	//路径匹配器
	private AntPathMatcher antPathMatcher = new AntPathMatcher();


	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		//得到需要验证码拦截的URL
	/*	String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(zuulProperties.getSmsCodeProperties().getUrl(), ",");
		if(!ObjectUtils.equals(configUrls, null)) {
			for (String configUrl : configUrls) {
				urls.add(configUrl);
			}
		}*/
		urls.add(ZuulAppConstant.SMS_URL);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean action = false;
		for (String validateUrl : urls) {
			//判断当前路径是否需要验证
			if (antPathMatcher.match(request.getRequestURI(), validateUrl)) {
				action = true;
			}
		}
		if(action) {
			try {
				validateCode(new ServletWebRequest(request));
			} catch (SMSException exception) {
				log.error("短信验证码验证错误,错误原因是:" + exception.getMessage());
				appAuthenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validateCode(ServletWebRequest request) throws ServletRequestBindingException {
		ValidateCode code =  validateCodeRepository.
				get(request, ZuulAppConstant.SMS_SESSION_KEY);
		String imageCode = ServletRequestUtils.getStringParameter(request.getRequest(), "sms_code");
		if(StringUtils.isBlank(imageCode)){
			throw new SMSException(SecurityEnum.SMS_CODE_ERROR_CODE);
		}
		if(code == null){
			throw new SMSException(SecurityEnum.SMS_CODE_EXIST);
		}
		if(code.isExpried()){
			validateCodeRepository.remove(request,ZuulAppConstant.SMS_SESSION_KEY);
			throw new SMSException(SecurityEnum.SMS_CODE_PAST);
		}
		if(!StringUtils.equals(imageCode,code.getCode())){
			throw new SMSException(SecurityEnum.SMS_CODE_ERROR);
		}
		validateCodeRepository.remove(request,ZuulAppConstant.SMS_SESSION_KEY);
	}

}
