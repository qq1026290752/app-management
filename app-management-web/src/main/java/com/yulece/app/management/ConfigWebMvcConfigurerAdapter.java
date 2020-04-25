package com.yulece.app.management;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ConfigWebMvcConfigurerAdapter
 * @Package com.yulece.app.management
 * @Description:
 * @Date 2020-04-17 22:16
 **/
@Configuration
public class ConfigWebMvcConfigurerAdapter implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/","/index.html");
  }
}
