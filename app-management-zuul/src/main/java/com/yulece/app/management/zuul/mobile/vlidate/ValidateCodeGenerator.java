package com.yulece.app.management.zuul.mobile.vlidate;

import javax.servlet.ServletRequest;


/**
 * 验证码实现接口
 * @author w4837
 *
 */
public interface ValidateCodeGenerator {
	/**
	 * 图片验证码实现接口
	 * @param request
	 * @return
	 */
	 ValidateCode createImageCode(ServletRequest request);
}
