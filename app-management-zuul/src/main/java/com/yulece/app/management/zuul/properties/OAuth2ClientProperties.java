package com.yulece.app.management.zuul.properties;

public class OAuth2ClientProperties {

	
	private String clientId = "app";
	
	private String clientSecret = "app_secret";
	//默认值为0 没有过期时间 
	private Integer accessTokenValiditySeconds = 60*60*12;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}
}
