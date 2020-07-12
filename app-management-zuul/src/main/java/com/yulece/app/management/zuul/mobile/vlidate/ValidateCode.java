package com.yulece.app.management.zuul.mobile.vlidate;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 验证码
 * @author w4837
 *
 */
public class ValidateCode implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//验证码
	private String code;
	//过期时间
	private LocalDateTime expireTime;
	
	public ValidateCode(String code,int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	public ValidateCode(String code,LocalDateTime expireTime) {
		this.code = code;
		this.expireTime =expireTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
}
