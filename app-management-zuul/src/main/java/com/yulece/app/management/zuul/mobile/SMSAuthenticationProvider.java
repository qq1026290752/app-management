package com.yulece.app.management.zuul.mobile;
 
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.Getter;
import lombok.Setter;

public class SMSAuthenticationProvider implements AuthenticationProvider {

	@Getter
	@Setter
	private  UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SMSAuthenticationToken authenticationToken = (SMSAuthenticationToken) authentication;
		UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		if(user == null) {
			//认证失败
			throw new InternalAuthenticationServiceException("无法从token中读取用户个人信息");
		}
		//认证成功
		SMSAuthenticationToken authenticationTokenResult = new SMSAuthenticationToken(user, user.getAuthorities());
		authenticationTokenResult.setDetails(authenticationToken.getDetails());
		return authenticationTokenResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//判断是否为短信登录token
		return SMSAuthenticationToken.class.isAssignableFrom(authentication);
	}

}