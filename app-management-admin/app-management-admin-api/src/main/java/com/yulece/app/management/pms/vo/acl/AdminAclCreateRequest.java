package com.yulece.app.management.pms.vo.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclCreateRequest
 * @Package com.yulece.app.management.pms.vo.acl
 * @Description:
 * @Date 2020-01-06 22:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAclCreateRequest {

    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 1,max = 60,message = "权限点名称请在1-60个字符之间")
    private String aclName;
    private String aclModuleId;
    private String aclUrl;
    private Integer type;
    private Integer status;
    private Integer aclSeq;
    private Integer aclRemark;
}
