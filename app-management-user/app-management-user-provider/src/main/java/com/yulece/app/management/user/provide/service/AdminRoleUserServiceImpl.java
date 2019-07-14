package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.user.api.AdminRoleUserService;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserRoleDto;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.param.AdminUserRoleParam;
import com.yulece.app.management.user.provide.pojo.AdminRoleUser;
import com.yulece.app.management.user.provide.repositories.AdminRoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleUserServiceImpl
 * @Package com.yulece.app.management.user.provide.service
 * @Description:
 * @Date 2019-06-02 17:01
 **/
@Service
public class AdminRoleUserServiceImpl implements AdminRoleUserService {

    @Autowired
    private AdminRoleUserRepository adminRoleUserRepository;
    @Autowired
    private AdminUserService adminUserService;

    @Override
    public AdminUserRoleDto addUserByRole(AdminUserRoleParam param) {
        //查询用户是否存在
        BeanValidator.check(param);
        AdminUserVo adminUserVo = adminUserService.getById(param.getUserId());
        BeanValidator.checkObjectNull(adminUserVo, AppParamEnum.USER_NOT_EXIST_ERROR);
        //删除用户当前角色
        adminRoleUserRepository.deleteUserId(param.getUserId());
        //为当前用户添加角色集合
        List<AdminRoleUser> adminRoleUsers =
                param.getRoleId().stream().map(roleId -> new AdminRoleUser(roleId,param.getUserId()
                        , LoginHandlerInterceptor.getCurrentUser()
                        , LoginHandlerInterceptor.getCurrentIp()))
                        .collect(Collectors.toList());
        adminRoleUserRepository.insertList(adminRoleUsers);
        //查询当前用户所有角色
        return  adminRoleUserRepository.findUserRoleListByUserId(param.getUserId());
    }

    @Override
    public AdminUserRoleDto findUserRole(Integer userId) {
        AdminUserVo adminUserVo = adminUserService.getById(userId);
        BeanValidator.checkObjectNull(adminUserVo, AppParamEnum.USER_NOT_EXIST_ERROR);
        return adminRoleUserRepository.findUserRoleListByUserId(userId);
    }
}
