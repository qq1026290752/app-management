package com.yulece.app.management.zuul.authorization;

import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.expression.AppSecurityExpressionHandler;
import com.yulece.app.management.zuul.mobile.SmsAuthorizationSecurityConfig;
import com.yulece.app.management.zuul.mobile.vlidate.ValidateCodeRepository;
import com.yulece.app.management.zuul.mobile.vlidate.sms.SmsValidateCodeFiler;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableResourceServer
public class AppResourceServiceAdapter extends ResourceServerConfigurerAdapter {

	private final AppSecurityExpressionHandler appSecurityExpressionHandler;
	private final ZuulProperties zuulProperties;
	private final AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	private final AuthenticationFailureHandler appAuthenticationFailureHandler;
	private final AccessDeniedHandler appAccessDeniedHandler;
	private final ValidateCodeRepository appValidateCodeRepository;
	private final ValidateCodeRepository sessionCodeRepository;
	private final SmsAuthorizationSecurityConfig smsAuthorizationSecurityConfig;

	public AppResourceServiceAdapter(AppSecurityExpressionHandler appSecurityExpressionHandler, ZuulProperties zuulProperties, AuthenticationSuccessHandler appAuthenticationSuccessHandler, AuthenticationFailureHandler appAuthenticationFailureHandler, AccessDeniedHandler appAccessDeniedHandler, ValidateCodeRepository appValidateCodeRepository, ValidateCodeRepository sessionCodeRepository, SmsAuthorizationSecurityConfig smsAuthorizationSecurityConfig) {
		this.appSecurityExpressionHandler = appSecurityExpressionHandler;
		this.zuulProperties = zuulProperties;
		this.appAuthenticationSuccessHandler = appAuthenticationSuccessHandler;
		this.appAuthenticationFailureHandler = appAuthenticationFailureHandler;
		this.appAccessDeniedHandler = appAccessDeniedHandler;
		this.appValidateCodeRepository = appValidateCodeRepository;
		this.sessionCodeRepository = sessionCodeRepository;
		this.smsAuthorizationSecurityConfig = smsAuthorizationSecurityConfig;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.expressionHandler(appSecurityExpressionHandler);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		//短信验证码 浏览器基于session开发
		SmsValidateCodeFiler smsValidateCodeFiler = new SmsValidateCodeFiler(sessionCodeRepository);
		smsValidateCodeFiler.setAppAuthenticationFailureHandler(appAuthenticationFailureHandler);
		smsValidateCodeFiler.setValidateCodeRepository(appValidateCodeRepository);
		//调用前置方法
		smsValidateCodeFiler.afterPropertiesSet();
		http.addFilterAfter(smsValidateCodeFiler, UsernamePasswordAuthenticationFilter.class)
				.formLogin()
				.loginProcessingUrl(ZuulAppConstant.LOGIN_URL)
				.loginPage(ZuulAppConstant.LOGIN_JUMP_CONTROLLER)
				.failureHandler(appAuthenticationFailureHandler)
				.successHandler(appAuthenticationSuccessHandler)
			.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET,
						zuulProperties.getAuth().toGetAdapter())
				.permitAll()
				.antMatchers(HttpMethod.POST,zuulProperties.getAuth().toPostAdapter())
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS)
				.permitAll()
			.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling().accessDeniedHandler(appAccessDeniedHandler)
		    .and()
			    .apply(smsAuthorizationSecurityConfig)
            .and()
				.csrf().disable();
	}

}
