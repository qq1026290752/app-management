package com.yulece.app.management.user.provide.enums;

public enum  AdminUserStatusEnum {

    USER_NON_ACTIVATED(0, "未激活"),
    USER_ACTIVATED(1, "激活"),

            ;

    private Integer code;

    private String message;

    AdminUserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
