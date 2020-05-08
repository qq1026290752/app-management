package com.yulece.app.management.pms.entity.tree;

import com.google.common.collect.Lists;
import com.yulece.app.management.pms.entity.AdminAclModule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleTreeDto
 * @Package com.yulece.app.management.pms.dto.acl.module
 * @Description:
 * @Date 2020-01-05 23:18
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminAclModuleTreeDto extends AdminAclModule {

    private List<AdminAclModuleTreeDto> aclModules = Lists.newArrayList();

    public static AdminAclModuleTreeDto adapt(AdminAclModule aclModule) {
        AdminAclModuleTreeDto dto = new AdminAclModuleTreeDto();
        BeanUtils.copyProperties(aclModule,dto);
        return dto;

    }
}
