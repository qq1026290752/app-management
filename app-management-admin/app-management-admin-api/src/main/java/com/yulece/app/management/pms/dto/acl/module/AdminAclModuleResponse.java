package com.yulece.app.management.pms.dto.acl.module;

import lombok.Data;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleResponse
 * @Package com.yulece.app.management.pms.dto.acl.module
 * @Description:
 * @Date 2020-01-05 20:38
 **/
@Data
public class AdminAclModuleResponse {

    private Integer aclModuleId;
    private String aclModuleName;
    private String parentAclModuleId;
    private Integer parentAclModuleName;
    private Integer status;
    private Date createTime;
    private Integer moduleSeq;
    private String moduleRemark;
}
