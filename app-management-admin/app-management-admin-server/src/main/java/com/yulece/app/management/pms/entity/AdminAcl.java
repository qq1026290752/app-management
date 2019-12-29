package com.yulece.app.management.pms.entity;


import lombok.Data;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAcl
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 16:55
 **/
@Data
public class AdminAcl {

    private Integer aclId;
    private String aclName;
    private String aclModuleId;
    private String aclUrl;
    private Integer type;
    private Integer status;
    private Integer aclSeq;
    private Integer aclRemark;
    private String operator;
    private String operateIp;
    private Date createTime;
    private Date updateTime;
}
