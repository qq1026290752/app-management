package com.yulece.app.management.pms.vo.user;

import com.yulece.app.management.commons.utils.page.PageParams;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserQueryRequest
 * @Package com.yulece.app.management.pms.vo.user
 * @Description:
 * @Date 2019-12-28 23:41
 **/
public class AdminUserQueryRequest extends PageParams {

    private String userName;
    private String nikeName;
    private Integer userDept;
    private Integer status;
    private String mail;
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Integer getUserDept() {
        return userDept;
    }

    public void setUserDept(Integer userDept) {
        this.userDept = userDept;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
