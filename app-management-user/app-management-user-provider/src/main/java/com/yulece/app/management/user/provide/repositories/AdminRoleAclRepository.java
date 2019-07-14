package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.user.dto.AdminRoleAclDto;
import com.yulece.app.management.user.provide.pojo.AdminRoleAcl;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleAclRepository
 * @Package com.yulece.app.management.user.provide.repositories
 * @Description: 角色 权限关联表仓储
 * @Date 2019-06-13 15:27
 **/
@Repository
public interface AdminRoleAclRepository extends AppUserProviderMapper<AdminRoleAcl> {
    /**
     * 根据角色 删除角色权限关联表
     * @param roleId
     */
   void deleteAllAclByRoleId(Integer roleId);

    AdminRoleAclDto findRoleAclByRoleId(@Param("roleId") Integer roleId);
}
