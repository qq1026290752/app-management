package com.yulece.app.management.user.provide.properties;

public class ServerProperties {

    private String domainName = "http://127.0.0.1";
    private String domainPort = "8080";

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainPort() {
        return domainPort;
    }

    public void setDomainPort(String domainPort) {
        this.domainPort = domainPort;
    }
}
