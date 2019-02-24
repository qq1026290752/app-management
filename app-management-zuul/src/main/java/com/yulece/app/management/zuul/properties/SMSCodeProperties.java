package com.yulece.app.management.zuul.properties;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: SMSCodeProperties
 * @Package com.yulece.app.management.zuul.properties
 * @Description:
 * @Date 2019-02-24 18:54
 **/
public class SMSCodeProperties {

    //验证码保存时间
    private int expireIn = 120;
    // 验证码长度
    private int codeLen = 6;
    //针对的路径
    private String url;

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public int getCodeLen() {
        return codeLen;
    }

    public void setCodeLen(int codeLen) {
        this.codeLen = codeLen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
