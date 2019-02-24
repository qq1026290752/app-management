package com.yulece.app.management.security.properties;

public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};

    private String oAuth2SigningKey = "yulece";

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getoAuth2SigningKey() {
        return oAuth2SigningKey;
    }

    public void setoAuth2SigningKey(String oAuth2SigningKey) {
        this.oAuth2SigningKey = oAuth2SigningKey;
    }
}
