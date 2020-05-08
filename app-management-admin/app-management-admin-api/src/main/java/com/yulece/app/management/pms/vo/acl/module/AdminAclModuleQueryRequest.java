package com.yulece.app.management.pms.vo.acl.module;

import com.yulece.app.management.commons.utils.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleQueryRequest
 * @Package com.yulece.app.management.pms.vo.acl.module
 * @Description:
 * @Date 2020-01-05 20:25
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminAclModuleQueryRequest extends PageParams {

    private String aclModuleName;
    private Integer parentAclModuleId;
    private Integer status;
}
