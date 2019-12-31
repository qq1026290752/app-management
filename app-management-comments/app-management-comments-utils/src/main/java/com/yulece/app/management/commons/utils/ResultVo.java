package com.yulece.app.management.commons.utils;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @title: ResultVo.java
 * @Package: com.yulece.common.utils
 * @author: wangyichao@yulece.com
 * @date: 创建时间2018年5月1日 - 下午4:48:02
 * @version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL )
public class ResultVo<T> {

	private int code;
	private String message;
	private T data;
	private String path;
	private Date date = new Date();
	private Integer status = 200;

	public ResultVo(int code, String message, int status) {
		this.code = code;
		this.message = message;
		this.status = status;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private ResultVo(Integer code, String message, T data,Integer status) {
		this.code = code;
		this.data = data;
		this.message = message;
		this.status = status;
	}

	public ResultVo(Integer code, String message,Date date,Integer status) {
		this.code = code;
		this.date = new Date();
		this.message = message;
		this.status = status;
	}

	public ResultVo(int code, String message, T data, String path, Date date, Integer status) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.path = path;
		this.date = date;
		this.status = status;
	}

	public ResultVo(int code, T data) {
		this.code = code;
		this.data = data;
		this.message = ResultEnum.SUCCESS.getMessage();
	}

	
	
	public ResultVo(String path) {
		super();
		this.code = ResultEnum.ERROR.getCode();
		this.message = "not fount rest path"; 
		this.path = path;
		this.date = new Date();
	}

	private ResultVo(Integer code) {
		this.code = code;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createSuccessResult(String message, T data) {
		return new ResultVo(ResultEnum.SUCCESS.getCode(), message, data,200);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createSuccessResult() {
		return new ResultVo(ResultEnum.SUCCESS.getCode(),null);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createErrorResult(String message, T data) {
		return new ResultVo(ResultEnum.ERROR.getCode(), message, data,500);
	}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createErrorResult() {
		return new ResultVo(ResultEnum.ERROR.getCode(),"error",500);
	}
	@SuppressWarnings({"unchecked",})
	public static <T> ResultVo createErrorResult(String message) {
		return new ResultVo(ResultEnum.ERROR.getCode(), message, null,500);
	}

	@SuppressWarnings({"unchecked",})
	public static <T> ResultVo createErrorResult(String message,Integer code) {
		return new ResultVo(ResultEnum.ERROR.getCode(), message, null,code);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createSuccessResult(T data) {
		return new ResultVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data,200);
	}


	public static <T> ResultVo<T> createSuccessResult(String message) {
		return new ResultVo(ResultEnum.SUCCESS.getCode(), message, null,200);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createErrorResult(T data) {
		return new ResultVo(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), data,200);
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createErrorResult(Integer code) {
		return new ResultVo(code);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <T> ResultVo<T> createErrorNotFountResult(String status,String path) {
		return new ResultVo(path);
	}

}