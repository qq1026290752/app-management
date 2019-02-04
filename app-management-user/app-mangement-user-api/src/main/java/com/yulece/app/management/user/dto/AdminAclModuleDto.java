package com.yulece.app.management.user.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleDto
 * @Package com.yulece.app.management.user.dto
 * @Description:
 * @Date 2019-01-10 21:37
 **/
@Data
public class AdminAclModuleDto {

    private Integer moduleId;

    private String moduleName;

    private Integer moduleParentId;

    private String moduleLevel;

    private Integer status;

    private Integer moduleSeq;

    private String moduleRemark;

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;

    private List<AdminAclModuleDto> adminAclModelDtos = Lists.newArrayList();
}
