package com.yulece.app.management.pms.entity;


import lombok.Data;
import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRole
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 16:11
 **/
@Data
public class AdminRole {

    private Integer roleId;
    private String roleName;
    private String roleType;
    private Integer roleStatus;
    private Integer roleSeq;
    private String roleRemark;
    private String operator;
    private String operateIp;
    private Date createTime;
    private Date updateTime;

}
