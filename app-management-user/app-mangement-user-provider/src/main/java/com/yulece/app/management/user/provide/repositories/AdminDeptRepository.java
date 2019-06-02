package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.user.param.AdminDeptParam;
import com.yulece.app.management.user.provide.pojo.AdminDept;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;

import java.util.List;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:AdminDeptRepository
 * @Package com.yulece.app.management.user.provide.repositories
 * @Description: 管理部门仓储
 * @Date 创建时间 2018/10/21-21:29
 **/
public interface AdminDeptRepository extends AppUserProviderMapper<AdminDept> {
    /**
     * 查询该部门下是否存在子部门
     * @param deptId
     * @return
     */
    Integer findAdminDeptByParentId(Integer deptId);

    /**
     * 查询部门下存在的用户
     * @param deptId
     * @return
     */

    Integer findUserCountByDeptId(Integer deptId);

    /**
     * 查询所有子等级部门
     * @param deptLevel
     * @return
     */
    List<AdminDept> getDeptChildListByLevel(String deptLevel);

    /**
     * 根据条件查询部门
     * @param adminDeptParam
     * @return
     */
    Integer existDeptByDept(AdminDeptParam adminDeptParam);
}
