package com.yulece.app.management.zuul.authorization;

import com.yulece.app.management.zuul.expression.AppSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@Order(6)
public class AppResourceServiceAdapter extends ResourceServerConfigurerAdapter {

	@Autowired
	private AppSecurityExpressionHandler appSecurityExpressionHandler;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.expressionHandler(appSecurityExpressionHandler);
	}

}
