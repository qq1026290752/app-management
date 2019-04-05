package com.yulece.app.management.zuul.constant;

public interface OauthAppConstant {

    String LOGIN_JUMP_CONTROLLER = "/authentication/require";
    String LOGIN_URL = "/authentication/form";
    String SMS_URL ="/authentication/mobile";
    //返回标准页面
    String CONTENT_TYPE_HTML = "text/html;charset=UTF-8";
    //返回JSON信息
    String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    String SMS_SESSION_KEY = "SMS_SESSION_KEY";
}
