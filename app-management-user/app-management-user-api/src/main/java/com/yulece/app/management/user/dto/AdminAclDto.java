package com.yulece.app.management.user.dto;

import lombok.Data;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclDto
 * @Package com.yulece.app.management.user.dto
 * @Description: ADMIN ACL DTO
 * @Date 2018-12-22 15:24
 **/
@Data
public class AdminAclDto {

    private Integer id;
    private String aclName;//权限点名称
    private String aclModelId;//权限模块ID
    private String aclModelName;//所属权限模块名称
    private Integer type;//权限点类型
    private String url;//权限点URL
    private Integer seq;//权限点排序
    private Integer status;//权限状
    private String aclRemark;//备注
    
}
