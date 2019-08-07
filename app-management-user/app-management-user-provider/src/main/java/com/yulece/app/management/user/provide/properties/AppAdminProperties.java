package com.yulece.app.management.user.provide.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.admin")
public class AppAdminProperties {

    private String notInterceptUrl;//不需要服务认证的URL

    private ServerProperties serverProperties = new ServerProperties();

    public ServerProperties getServerProperties() {
        return serverProperties;
    }

    public void setServerProperties(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    public String getNotInterceptUrl() {
        return notInterceptUrl;
    }

    public void setNotInterceptUrl(String notInterceptUrl) {
        this.notInterceptUrl = notInterceptUrl;
    }
}
