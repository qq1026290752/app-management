package com.yulece.app.management.user.provide.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:LevelUtil
 * @Package com.yulece.app.management.user.provide.utils
 * @Description: 定义属性结构
 * @Date 创建时间 2018/10/21-21:36
 **/
public class LevelUtil {

    private static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    public static String calculateLevel(String parentLevel,Integer parentId){
        if(StringUtils.isBlank(parentLevel)){
            return ROOT;
        }else {
            return StringUtils.join(parentLevel,SEPARATOR,parentId);
        }
    }
}
