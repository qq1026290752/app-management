package com.yulece.app.management.user.param;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleAclParam
 * @Package com.yulece.app.management.user.param
 * @Description: 角色权限点参数
 * @Date 2019-06-04 22:21
 **/
public class AdminRoleAclParam {


    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
    @NotNull(message = "权限点ID不能为空")
    private List<Integer> aclIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getAclIds() {
        return aclIds;
    }

    public void setAclIds(List<Integer> aclIds) {
        this.aclIds = aclIds;
    }
}
