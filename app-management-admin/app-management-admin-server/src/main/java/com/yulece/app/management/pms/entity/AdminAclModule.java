package com.yulece.app.management.pms.entity;

import lombok.Data;

import java.util.Date;
/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModule
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 16:36
 **/
@Data
public class AdminAclModule {

    private Integer moduleId;
    private String moduleName;
    private Integer moduleParentId;
    private String moduleLevel;
    private String status;
    private String moduleSeq;
    private String moduleRemark;
    private String operator;
    private String operateIp;
    private Date createTime;
    private Date updateTime;

}
