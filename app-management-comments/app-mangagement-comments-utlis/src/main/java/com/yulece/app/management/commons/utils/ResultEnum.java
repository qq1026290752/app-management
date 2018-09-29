package com.yulece.app.management.commons.utils;

public enum ResultEnum {
	SUCCESS(0,"success"),
    ERROR(1,"error")
    ;

    private int code;
    private String message;

    ResultEnum(Integer code, String message){
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
