package com.yulece.app.management.user.entity;

import com.yulece.app.management.comments.api.entity.PageParms;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleParam
 * @Package com.yulece.app.management.user.entity
 * @Description:
 * @Date 2019-03-10 09:27
 **/
@Data
public class AdminRoleParam extends PageParms {

    private Integer roleId;

    @NotBlank(message = "角色名称不能为空")
    @Length(min = 3,max = 20,message = "角色长名称度不能小于3而且不能大于20")
    private String roleName;

    @NotNull(message = "角色类型不能为空")
    @Min(value = 1,message = "角色类型不正确")
    @Max(value = 3,message = "角色类型不正确")
    private Integer roleType;

    @NotNull(message = "角色状态不能为空")
    @Min(value = 0,message = "角色状态不正确")
    @Max(value = 2,message = "角色状态不正确")
    private Byte roleStatus;

    @NotNull(message = "角色所在位置不能为空")
    private Integer roleSeq;

    @Length(min = 10,max = 128,message = "角色备注长度请在10-128之间")
    private String roleRemark;
}
