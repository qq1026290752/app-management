package com.yulece.app.management.commons.exception;


import com.yulece.app.management.commons.enums.ExceptionEnum;
import com.yulece.app.management.commons.enums.ParamEnum;

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
	public AppException(ParamEnum paramEnum) {
		super(paramEnum.getMessage());
		this.code = paramEnum.getCode();
	}

	public AppException(Integer code, String message) {
		super(message);
		this.code = code;
	}

}