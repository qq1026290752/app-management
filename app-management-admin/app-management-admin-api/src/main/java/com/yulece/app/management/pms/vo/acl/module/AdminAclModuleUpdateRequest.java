package com.yulece.app.management.pms.vo.acl.module;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleUpdateRequest
 * @Package com.yulece.app.management.pms.vo.acl.module
 * @Description:
 * @Date 2020-01-05 20:22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminAclModuleUpdateRequest extends AdminAclModuleCreateRequest{
    private Integer aclModuleId;
}
