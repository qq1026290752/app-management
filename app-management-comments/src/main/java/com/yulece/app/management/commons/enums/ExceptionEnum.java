package com.yulece.app.management.commons.enums;

public class ExceptionEnum {

		;
	    private int code;
	    private String message;

	    ExceptionEnum(Integer code, String message){
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
