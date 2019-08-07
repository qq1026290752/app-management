package com.yulece.app.management.user.param;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserRoleParam
 * @Package com.yulece.app.management.user.param
 * @Description: 用户角色表入参
 * @Date 2019-06-02 21:18
 **/
public class AdminUserRoleParam{


    private Integer roleUserId;
    @NotNull(message = "角色集合不能为空")
    private List<Integer> roleId;
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    public Integer getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(Integer roleUserId) {
        this.roleUserId = roleUserId;
    }

    public List<Integer> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Integer> roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
