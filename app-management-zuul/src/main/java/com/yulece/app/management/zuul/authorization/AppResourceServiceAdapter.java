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

	@Autowired
	private AppSecurityExpressionHandler appSecurityExpressionHandler;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.expressionHandler(appSecurityExpressionHandler);
	}

	@Autowired
	private ZuulProperties zuulProperties;
	@Autowired
	private AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler appAuthenticationFailureHandler;
	@Autowired
	private AccessDeniedHandler appAccessDeniedHandler;
	@Autowired
	private ValidateCodeRepository appValidateCodeRepository;
	@Autowired
	private ValidateCodeRepository sessionCodeRepository;
	@Autowired
    private SmsAuthorizationSecurityConfig smsAuthorizationSecurityConfig;

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
				.authorizeRequests()
				.anyRequest()
				.access("@defaultZuulAuthorizationService.hasPermission(request,authentication)")
		    .and()
			    .apply(smsAuthorizationSecurityConfig)
            .and()
				.csrf().disable();
	}

}
