package com.yulece.app.management.commons.utils.enums;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: SecurityEnum
 * @Package com.yulece.app.management.commons.utils.enums
 * @Description:
 * @Date 2019-02-24 19:10
 **/
public enum  SecurityEnum {

    SMS_CODE_ERROR_CODE(100,"请求头携带的deviceId不能为空"),
    SMS_CODE_EMPTY(99,"验证码不能为空"),
    SMS_CODE_PAST(98,"验证码已过期"),
    SMS_CODE_EXIST(97,"验证码不存在"),
    SMS_CODE_ERROR(96,"验证码输入错误")
    ;
    private int code;
    private String message;

    SecurityEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
