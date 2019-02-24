package com.yulece.app.management.zuul.mobile.vlidate;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeRepository {
	/**
	 * 保存验证码
	 * @param request
	 * @param validateCode
	 * @param validateCodeKey
	 */
	void save(ServletWebRequest request, ValidateCode validateCode, String validateCodeKey);
	/**
	 * 获取验证码
	 * @param request
	 * @param validateCodeKey
	 */
	ValidateCode get(ServletWebRequest request, String validateCodeKey);
	/**
	 * 删除验证码
	 * @param request
	 * @param validateCodeKey
	 */
	void remove(ServletWebRequest request, String validateCodeKey);
}
