package com.yulece.app.management.zuul.properties;

public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};

    private String oauth2SigningKey = "yulece";

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getOauth2SigningKey() {
        return oauth2SigningKey;
    }

    public void setOauth2SigningKey(String oauth2SigningKey) {
        this.oauth2SigningKey = oauth2SigningKey;
    }
}
