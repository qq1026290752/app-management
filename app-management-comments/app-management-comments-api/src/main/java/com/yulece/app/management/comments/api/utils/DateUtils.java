package com.yulece.app.management.comments.api.utils;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: com.yulece.app.management.comments.api.utils.DateUtils
 * @Package PACKAGE_NAME
 * @Description:
 * @Date 2019-06-02 09:11
 **/
public class DateUtils {

    public static CustomDateEditor fromCustomDateEditor(String formatDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
        return new CustomDateEditor(simpleDateFormat,true);
    }

    public enum FormDateType {
        BASIC("yyyy-MM-dd HH:mm:ss"),
        DATE("yyyy-MM-dd"),
        YEAR("yyyy"),
        STRING("yyyyMMddHHmmss"),
        ;

        public String format;

        FormDateType(String format) {
            this.format = format;
        }
    }

}
