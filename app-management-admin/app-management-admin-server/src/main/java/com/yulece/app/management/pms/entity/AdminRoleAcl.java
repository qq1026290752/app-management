package com.yulece.app.management.pms.entity;


import lombok.Data;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleAcl
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 17:17
 **/
@Data
public class AdminRoleAcl {
    private Integer roleAclId;
    private Integer roleId;
    private Integer aclId;
    private String operator;
    private String operateIp;
    private Date createTime;
    private Date updateTime;
}
