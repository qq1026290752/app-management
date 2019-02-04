package com.yulece.app.management.user.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AdminAclModuleParam {

    private Integer moduleId;

    @NotBlank(message = "模块名称不能为空")
    @Length(min = 1, max = 20, message = "模块名称的长度需要在1-20字符之间")
    private String moduleName;

    @NotNull(message = "模块父ID不能为空")
    private Integer moduleParentId;

    @NotNull(message = "模块状态不能为空")
    @Min(value = 0, message = "模块状态不正确")
    @Max(value = 1, message = "模块状态不正确")
    private Integer status;

    @NotNull(message = "模块排序不能为空")
    private Integer moduleSeq;

    @NotBlank(message = "模块备注不能为空")
    @Length(min = 1, max = 64, message = "模块备注的长度需要在1-64字符之间")
    private String moduleRemark;

}
