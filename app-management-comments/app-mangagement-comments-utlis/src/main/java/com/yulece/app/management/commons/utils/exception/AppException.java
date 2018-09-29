package com.yulece.app.management.commons.utils.exception;


import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.enums.ExceptionEnum;

public class AppException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 3393688888698161757L;
	private Integer code;

	public AppException(ExceptionEnum exceptionEnum) {
		super(exceptionEnum.getMessage());
		this.code = exceptionEnum.getCode();
	}
	public AppException(AppParamEnum paramEnum) {
		super(paramEnum.getMessage());
		this.code = paramEnum.getCode();
	}

	public AppException(Integer code, String message) {
		super(message);
		this.code = code;
	}

}