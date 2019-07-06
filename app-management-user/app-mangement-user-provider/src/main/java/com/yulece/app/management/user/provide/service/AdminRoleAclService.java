package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.user.dto.AdminRoleAclDto;
import com.yulece.app.management.user.provide.pojo.AdminRoleAcl;
import com.yulece.app.management.user.provide.repositories.AdminRoleAclRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleAclService
 * @Package com.yulece.app.management.user.provide.service
 * @Description:
 * @Date 2019-06-13 15:24
 **/
@Service
public class AdminRoleAclService  {

    @Autowired
    private AdminRoleAclRepository adminRoleAclRepository;

    /**
     * 删除角色对应的权限信息
     * @param roleId
     */
    void deleteAllAclByRoleId(Integer roleId) {
        adminRoleAclRepository.deleteAllAclByRoleId(roleId);
    }

    /**
     * 保存角色权限点
     * @param newRoleAcls
     */
    void addRoleAcl(List<AdminRoleAcl> newRoleAcls) {
        adminRoleAclRepository.insertList(newRoleAcls);
    }

    AdminRoleAclDto findRoleAclByRoleId(Integer roleId) {
        return adminRoleAclRepository.findRoleAclByRoleId(roleId);
    }
}
