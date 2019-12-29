package com.yulece.app.management.pms.entity;

import lombok.Data;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUser
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 15:46
 **/
@Data
public class AdminUser {

    private Integer userId;
    private String userName;
    private String passWord;
    private String nikeName;
    private String phone;
    private String mail;
    private String headerUrl;
    private Integer status;
    private String userRemark;
    private Integer userDept;
    private Integer operator;
    private String operateIp;
    private Date createTime;
    private Date updateTime;
}
