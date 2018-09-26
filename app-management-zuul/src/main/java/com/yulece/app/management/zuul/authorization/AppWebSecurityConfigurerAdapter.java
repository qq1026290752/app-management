package com.yulece.app.management.zuul.authorization;

import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@Order(4)
@EnableResourceServer
public class AppWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private ZuulProperties zuulProperties;
	@Autowired
	private AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler appAuthenticationFailureHandler;
	@Autowired
	private AccessDeniedHandler appAccessDeniedHandler;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
				.loginPage(ZuulAppConstant.LOGIN_JUMP_CONTROLLER)
				.loginProcessingUrl(ZuulAppConstant.LOGIN_URL)
				.successHandler(appAuthenticationSuccessHandler)
				.failureHandler(appAuthenticationFailureHandler)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET,zuulProperties.getAuth().toGetAdapter())
				.permitAll()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST,zuulProperties.getAuth().toPostAdapter())
				.permitAll()
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling().accessDeniedHandler(appAccessDeniedHandler)
				.and()
				.csrf().disable();
	}


}
