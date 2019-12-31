package com.yulece.app.management.commons.utils.annotation;

import com.yulece.app.management.commons.utils.config.SpringExceptionResolver;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 注解类
 *
 * @author wangyichao@28ph.cn
 * @Title: EnableExceptionUtils
 * @Package com.yulece.app.management.commons.utils.annotation
 * @Description:
 * @Date 2019-11-17 12:20
 **/
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ExceptionConfig.class)
public @interface EnableExceptionUtils {
}
