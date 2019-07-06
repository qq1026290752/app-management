package com.yulece.app.management.user.dto;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleAclDto
 * @Package com.yulece.app.management.user.dto
 * @Description: 角色 权限点
 * @Date 2019-06-04 22:15
 **/
public class AdminRoleAclDto {

    private Integer roleId;
    private String roleName;
    private List<AdminAclDto> adminAclDtoList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<AdminAclDto> getAdminAclDtoList() {
        return adminAclDtoList;
    }

    public void setAdminAclDtoList(List<AdminAclDto> adminAclDtoList) {
        this.adminAclDtoList = adminAclDtoList;
    }
}
