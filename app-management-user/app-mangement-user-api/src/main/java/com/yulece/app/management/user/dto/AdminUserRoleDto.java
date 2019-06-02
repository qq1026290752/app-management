package com.yulece.app.management.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserRoleDto
 * @Package com.yulece.app.management.user.dto
 * @Description: 
 * @Date 2019-06-02 21:37
 **/
@Data
public class AdminUserRoleDto implements Serializable {

    private static final long serialVersionUID = -7955611375135316821L;
    private Integer userId;
    private String userName;
    private List<AdminRoleDto> adminRoles;
}
