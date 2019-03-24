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
@EqualsAndHashCode(callSuper = false)
public class AdminRoleDto extends AdminRoleParam {

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;
}
