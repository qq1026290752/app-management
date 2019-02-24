package com.yulece.app.management.security.authorization;

import com.yulece.app.management.security.properties.OAuth2ClientProperties;
import com.yulece.app.management.security.properties.SSOProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAuthorizationServer
public class SSOAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final SSOProperties ssoProperties;
	private final TokenStore tokenStore ;
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	@Autowired(required = false)
	private TokenEnhancer jwtTokenEnhancer;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SSOAuthorizationServerConfig(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, SSOProperties ssoProperties, TokenStore tokenStore, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.ssoProperties = ssoProperties;
		this.tokenStore = tokenStore;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		OAuth2ClientProperties[] clientProperties = ssoProperties.getOauth().getClients();
		if(ArrayUtils.isNotEmpty(ssoProperties.getOauth().getClients())) {
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
				   .redirectUris("http://api.yulece.com")
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
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security.tokenKeyAccess("isAuthenticated()");
	}

}
