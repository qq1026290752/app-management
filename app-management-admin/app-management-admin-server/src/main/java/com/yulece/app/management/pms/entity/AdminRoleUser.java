package com.yulece.app.management.pms.entity;

import lombok.Data;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleUser
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 17:09
 **/
@Data
public class AdminRoleUser {

    private Integer roleUserId;
    private Integer roleId;
    private Integer userId;
    private String operator;
    private String operateIp;
    private Date createTime;
    private Date updateTime;

}
