package com.yulece.app.management.pms.vo.acl.module;

import com.yulece.app.management.pms.entity.AdminAclModule;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleCreateRequset
 * @Package com.yulece.app.management.pms.vo.acl.module
 * @Description:
 * @Date 2020-01-05 20:09
 **/
@Data
public class AdminAclModuleCreateRequest {

    @NotBlank(message = "权限名称模块名称不能为空")
    @Length(min = 1,max = 60,message = "权限名称请在1-60个字符之间")
    private String moduleName;
    @NotNull(message = "上级权限模块不能为空")
    private Integer moduleParentId;
    @NotNull(message = "权限模块状态不能为空")
    private Integer status;
    @NotNull(message = "权限模块权限层次不能为空")
    private Integer moduleSeq;
    @Length(min = 1,max = 60,message = "权限备注请在1-255个字符之间")
    private String moduleRemark;

    public static String LEVEL = "0";

    public static String getLevel(AdminAclModule adminAclModule){
        if(adminAclModule==null){
            return LEVEL;
        }else {
            return adminAclModule.getModuleLevel().concat(",").concat(String.valueOf(adminAclModule.getModuleId()));
        }
    }

    public static String getLevel(String level,Integer moduleParentId){
        return level.concat(",").concat(String.valueOf(level));
    }
}
