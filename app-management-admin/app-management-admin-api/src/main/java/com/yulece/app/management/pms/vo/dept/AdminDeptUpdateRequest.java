package com.yulece.app.management.pms.vo.dept;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptCreateRequest
 * @Package com.yulece.app.management.pms.vo
 * @Description:
 * @Date 2019-11-10 14:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDeptUpdateRequest extends AdminDeptCreateRequest{
    @NotNull(message = "更新程序必须携带ID")
    private Integer deptId;
}
