package com.yulece.app.management.zuul.configurer;
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {

	private final static Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	private final PasswordEncoder passwordEncoder;
	public AppUserDetailsService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	/**
	 * 表单登录
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return buildUser(userName);
	}
	//用户必须要有ROLE_USER 才可以登录 服务提供商
	private UserDetails buildUser(String userId) {
		// 根据用户名查找用户信息


		//根据查找到的用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		LOGGER.info("数据库密码是:"+password);
		return new User(userId, password,
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
	}



}
