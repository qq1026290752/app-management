package com.yulece.app.management.zuul.authorization;

import java.util.ArrayList;
import java.util.List;

import com.yulece.app.management.zuul.properties.OAuth2ClientProperties;
import com.yulece.app.management.zuul.properties.ZuulProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


//实现认证服务器
@Configuration
public class AppAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private final AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ZuulProperties zuulProperties;
 	@Autowired
	private TokenStore tokenStore ;
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AppAuthorizationServerConfig(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		OAuth2ClientProperties[] clientProperties = zuulProperties.getOauth().getClients();
		if(ArrayUtils.isNotEmpty(zuulProperties.getOauth().getClients())) {
			for (OAuth2ClientProperties oAuth2ClientProperties : clientProperties) {
				 builder.withClient(oAuth2ClientProperties.getClientId())
				   .secret(oAuth2ClientProperties.getClientSecret())
				   //token有效时间
				   .accessTokenValiditySeconds(oAuth2ClientProperties.getAccessTokenValiditySeconds())
				   //验证模式
				   .authorizedGrantTypes("password","authorization_code","client_credentials","implicit","refresh_token")
				   //刷新时间
				   .refreshTokenValiditySeconds(3600*24*100)
					//跳转地址
				   .redirectUris("ws.28ph.cn")
				   //权限
				   .scopes("all");
			}
		} 
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore)
				.userDetailsService(userDetailsService)
				.reuseRefreshTokens(true);
		if(jwtAccessTokenConverter != null && jwtTokenEnhancer!=null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
			tokenEnhancers.add(jwtTokenEnhancer);
			tokenEnhancers.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(tokenEnhancers);
			endpoints
					.tokenEnhancer(enhancerChain)
					.accessTokenConverter(jwtAccessTokenConverter);
		}
	}


	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients()
				.passwordEncoder(passwordEncoder)
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}

}
