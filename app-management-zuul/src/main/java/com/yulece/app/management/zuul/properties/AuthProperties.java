package com.yulece.app.management.zuul.properties;

import com.google.common.collect.Lists;
import com.yulece.app.management.zuul.constant.ZuulAppConstant;

import java.util.List;

public class AuthProperties {


    private List<String> methodGetUrl = Lists.newArrayList(ZuulAppConstant.LOGIN_JUMP_CONTROLLER,
            ZuulAppConstant.LOGIN_URL);
    private List<String> methodPostUrl = Lists.newArrayList("hello");

    public List<String> getMethodGetUrl() {
        return methodGetUrl;
    }

    public void setMethodGetUrl(List<String> methodGetUrl) {
        this.methodGetUrl = methodGetUrl;
    }

    public List<String> getMethodPostUrl() {
        return methodPostUrl;
    }

    public void setMethodPostUrl(List<String> methodPostUrl) {
        this.methodPostUrl = methodPostUrl;
    }

    public String[] toGetAdapter(){
        if(methodGetUrl.size() > 0 ){
            String[] toBeStored = methodGetUrl.toArray(new String[methodGetUrl.size()]);
            return toBeStored;
        }
        return new String[]{""};
    }
    public String[] toPostAdapter(){
        if(methodPostUrl.size() > 0 ){
            String[] toBeStored = methodPostUrl.toArray(new String[methodPostUrl.size()]);
            return toBeStored;
        }
        return new String[]{""};
    }

}
