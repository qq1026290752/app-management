package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.user.provide.pojo.AdminAcl;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclRepository
 * @Package com.yulece.app.management.user.provide.repositories
 * @Description:
 * @Date 2018-12-22 16:42
 **/
public interface AdminAclRepository  extends AppUserProviderMapper<AdminAcl> {
    /**
     * 根据模块信息查询模块记录数
     * @param adminAcl
     * @return
     */
    Integer findAdminAclIsExistByAdminAcl(AdminAcl adminAcl);
}
