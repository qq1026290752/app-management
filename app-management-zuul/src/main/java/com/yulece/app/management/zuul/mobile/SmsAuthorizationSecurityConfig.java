package com.yulece.app.management.zuul.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
@Component
public class SmsAuthorizationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler appAuthenticationFailureHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		SMSAuthenticationFilter smsAuthenticationFilter = new SMSAuthenticationFilter();
		smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		//成功跳转
		smsAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
		//失败跳转
		smsAuthenticationFilter.setAuthenticationFailureHandler(appAuthenticationFailureHandler);
		//
		SMSAuthenticationProvider smsAuthenticationProvider = new SMSAuthenticationProvider();
		smsAuthenticationProvider.setUserDetailsService(userDetailsService);
		http.authenticationProvider(smsAuthenticationProvider)
			.addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//短信验证码加入过滤器
	}
}
