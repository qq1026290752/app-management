package com.yulece.app.management.zuul.authorization;

import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class AppWebSecurityConfigurerAdapter extends ResourceServerConfigurerAdapter {

	private final ZuulProperties zuulProperties;
	private final AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	private final AuthenticationFailureHandler appAuthenticationFailureHandler;
	private final AccessDeniedHandler appAccessDeniedHandler;

	public AppWebSecurityConfigurerAdapter(ZuulProperties zuulProperties, AuthenticationSuccessHandler appAuthenticationSuccessHandler, AuthenticationFailureHandler appAuthenticationFailureHandler, AccessDeniedHandler appAccessDeniedHandler) {
		this.zuulProperties = zuulProperties;
		this.appAuthenticationSuccessHandler = appAuthenticationSuccessHandler;
		this.appAuthenticationFailureHandler = appAuthenticationFailureHandler;
		this.appAccessDeniedHandler = appAccessDeniedHandler;
	}


	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		super.configure(resources);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
				.failureHandler(appAuthenticationFailureHandler)
				.successHandler(appAuthenticationSuccessHandler)
				.loginPage(ZuulAppConstant.LOGIN_URL)
				.loginProcessingUrl(ZuulAppConstant.LOGIN_JUMP_CONTROLLER)
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
