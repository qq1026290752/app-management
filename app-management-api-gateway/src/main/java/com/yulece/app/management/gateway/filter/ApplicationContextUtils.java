package com.yulece.app.management.gateway.filter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: ApplicationContextUtils
 * @Package com.yulece.app.management.gateway.filter
 * @Description:
 * @Date 2019-03-30 21:02
 **/
public class ApplicationContextUtils {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext!=null){
            this.applicationContext = applicationContext;
        }
    }

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);

    }

}
