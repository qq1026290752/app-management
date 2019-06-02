package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminRoleService;
import com.yulece.app.management.user.dto.AdminRoleDto;
import com.yulece.app.management.user.param.AdminRoleParam;
import com.yulece.app.management.user.provide.pojo.AdminRole;
import com.yulece.app.management.user.provide.repositories.AdminRoleRepository;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleServiceImpl
 * @Package com.yulece.app.management.user.provide.service
 * @Description:
 * @Date 2019-03-17 13:48
 **/
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    @Override
    public Boolean save(AdminRoleParam param) {
        BeanValidator.check(param);
        if(checkRoleNameExist(param.getRoleName(),param.getRoleId())){
            throw  new AppException(AppParamEnum.ROLE_NAME_EXIST);
        }
        if(checkRoleSeqExist(param.getRoleSeq(),param.getRoleId())){
            throw new AppException(AppParamEnum.ROLE_SEQ_EXIST);
        }
        AdminRole adminRole = PojoConvertUtil.convertPojo(param, AdminRole.class);
        adminRole.setOperateIp(LoginHandlerInterceptor.getCurrentIp());
        adminRole.setOperator(LoginHandlerInterceptor.getCurrentUser());
        int insert = adminRoleRepository.insert(adminRole);
        return insert > 0;
    }

    private boolean checkRoleSeqExist(Integer roleSeq,Integer roleId) {
        AdminRole adminRole = new AdminRole();
        adminRole.setRoleId(roleId);
        adminRole.setRoleSeq(roleSeq);
        return adminRoleRepository.getAdminRoleByAdminRole(adminRole).size() > 0 ;
    }

    private boolean checkRoleNameExist(String roleName,Integer roleId) {
        AdminRole adminRole = new AdminRole();
        adminRole.setRoleId(roleId);
        adminRole.setRoleName(roleName);
        return adminRoleRepository.getAdminRoleByAdminRole(adminRole).size() > 0 ;
    }

    @Override
    public AdminRoleDto getById(Integer id) {
        AdminRole adminRole = adminRoleRepository.selectByPrimaryKey(id);
        return  PojoConvertUtil.convertPojo(BeanValidator.chekObjectNull(adminRole,AppParamEnum.ROLE_NOT_EXIST),
                AdminRoleDto.class);
    }

    @Override
    public Boolean update(AdminRoleParam param) {
        BeanValidator.check(param);
        if(checkRoleNameExist(param.getRoleName(),param.getRoleId())){
            throw  new AppException(AppParamEnum.ROLE_NAME_EXIST);
        }
        if(checkRoleSeqExist(param.getRoleSeq(),param.getRoleId())){
            throw new AppException(AppParamEnum.ROLE_SEQ_EXIST);
        }
        AdminRole adminRole = adminRoleRepository.selectByPrimaryKey(param.getRoleId());
        BeanValidator.chekObjectNull(adminRole,AppParamEnum.ROLE_NOT_EXIST);
        AdminRole updateAdminRole = PojoConvertUtil.convertPojo(param, AdminRole.class);
        return adminRoleRepository.updateByPrimaryKeySelective(updateAdminRole) > 0;
    }


    @Override
    public Boolean delete(Integer id) {
        return adminRoleRepository.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Page<AdminRoleDto> findAdminRoleByPage(AdminRoleParam param) {
        Page<AdminRoleDto> page = new Page<>(param.getPageNo(),param.getPageSize());
        page.setResult(adminRoleRepository.findAdminRoleByPage(page,param));
        return page;
    }
}
