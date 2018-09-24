package com.yulece.app.management.user.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdminAclParam {

    private Integer aclId;
    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 1, max = 20, message = "权限点的名称长度请在1-20字符之间")
    private String aclName;

    @NotNull(message = "权限模块不能为空")
    private Integer aclModuleId;

    @NotBlank(message = "权限点URL不能为空")
    @Length(min = 1, max = 200, message = "权限点的地址长度请在1-200字符之间")
    private String aclUrl;

    @NotNull(message = "权限点类型不能为空")
    @Max(value = 3, message = "权限点类型不正确")
    @Min(value = 1, message = "权限点类型不正确")
    private Integer type;

    @NotNull(message = "权限点状态不能为空")
    @Min(value = 0, message = "权限点状态不正确")
    private Integer status;

    @NotNull(message = "权限点所在位置不能为空")
    private Integer aclSeq;

    @NotBlank(message = "权限点备注不能为空")
    @Length(min = 1, max = 64, message = "权限点的备注长度请在1-64字符之间")
    private String aclRemark;
}
