package com.yulece.app.management.zuul.authorization;

import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class AppWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	private ZuulProperties zuulProperties;
	@Autowired
	private AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler appAuthenticationFailureHandler;
	@Autowired
	private AccessDeniedHandler appAccessDeniedHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.failureHandler(appAuthenticationFailureHandler)
				.successHandler(appAuthenticationSuccessHandler)
				.loginPage(ZuulAppConstant.LOGIN_URL)
				.loginProcessingUrl(ZuulAppConstant.LOGIN_JUMP_CONTROLLER)
			.and()
				.csrf().disable()
				.cors().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS,"/oauth/token","/oauth/check_token", "/rest/**", "/api/**", "/**")
				.permitAll()
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
				.authorizeRequests()
				.anyRequest()
				.access("@defaultZuulAuthorizationService.hasPermission(request,authentication)");
	}

}
