package com.yulece.app.management.commons.utils.enums;

public enum ExceptionEnum {
		ACTIVE_FAILURE(501,"账户激活失败,请稍后重试"),
		QUERY_ERROR(502,"查询不存在"),
		LOGIN_EXPIRES(402,"用户登陆过期,刷新token过期")
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
