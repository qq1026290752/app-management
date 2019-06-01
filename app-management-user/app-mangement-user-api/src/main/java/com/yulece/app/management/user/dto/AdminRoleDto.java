package com.yulece.app.management.user.dto;

import com.yulece.app.management.user.entity.AdminRoleParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleDto
 * @Package com.yulece.app.management.user.dto
 * @Description:
 * @Date 2019-03-10 10:27
 **/
@Data
public class AdminRoleDto {

    private Integer roleId;

    private String roleName;

    private Integer roleType;

    private Byte roleStatus;

    private Integer roleSeq;

    private String roleRemark;

    private Date createTime;
}
