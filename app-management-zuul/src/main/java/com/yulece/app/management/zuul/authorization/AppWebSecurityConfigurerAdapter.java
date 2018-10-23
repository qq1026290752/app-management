package com.yulece.app.management.zuul.authorization;

import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import com.yulece.app.management.zuul.expression.AppSecurityExpressionHandler;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfigurerAdapter extends ResourceServerConfigurerAdapter {

	private final AppSecurityExpressionHandler appSecurityExpressionHandler;
	private final ZuulProperties zuulProperties;
	private final AuthenticationSuccessHandler appAuthenticationSuccessHandler;
	private final AuthenticationFailureHandler appAuthenticationFailureHandler;
	private final AccessDeniedHandler appAccessDeniedHandler;

	public AppWebSecurityConfigurerAdapter(AppSecurityExpressionHandler appSecurityExpressionHandler, ZuulProperties zuulProperties, AuthenticationSuccessHandler appAuthenticationSuccessHandler, AuthenticationFailureHandler appAuthenticationFailureHandler, AccessDeniedHandler appAccessDeniedHandler) {
		this.appSecurityExpressionHandler = appSecurityExpressionHandler;
		this.zuulProperties = zuulProperties;
		this.appAuthenticationSuccessHandler = appAuthenticationSuccessHandler;
		this.appAuthenticationFailureHandler = appAuthenticationFailureHandler;
		this.appAccessDeniedHandler = appAccessDeniedHandler;
	}



	@Override
	@Order(Ordered.LOWEST_PRECEDENCE)
	public void configure(HttpSecurity http) throws Exception {
		http
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
				.formLogin()
				.failureHandler(appAuthenticationFailureHandler)
				.successHandler(appAuthenticationSuccessHandler)
				.loginPage(ZuulAppConstant.LOGIN_URL)
				.permitAll()
				.loginProcessingUrl(ZuulAppConstant.LOGIN_JUMP_CONTROLLER)
				.permitAll()
			.and()
				.authorizeRequests()
				.anyRequest()
				.access("@defaultZuulAuthorizationService.hasPermission(request,authentication)");;
	}


	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		// TODO Auto-generated method stub
		resources.expressionHandler(appSecurityExpressionHandler);
	}
}
