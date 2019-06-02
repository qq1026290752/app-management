package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.user.dto.AdminUserRoleDto;
import com.yulece.app.management.user.provide.pojo.AdminRoleUser;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 接口类
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleUserRepository
 * @Package com.yulece.app.management.user.provide.repositories
 * @Description: 用户角色存储类
 * @Date 2019-06-02 21:56
 **/
public interface AdminRoleUserRepository extends AppUserProviderMapper<AdminRoleUser> {
    /**
     * 根据用户ID查询用户角色集合
     * @param userId
     * @return
     */
    AdminUserRoleDto findUserRoleListByUserId(Integer userId);
}
