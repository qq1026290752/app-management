package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminRoleService;
import com.yulece.app.management.user.dto.AdminRoleDto;
import com.yulece.app.management.user.entity.AdminRoleParam;
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
    @Override
    public Boolean save(AdminRoleParam param) {
        BeanValidator.check(param);
        if(checkRoleNameExist(param.getRoleName())){
            throw  new AppException(AppParamEnum.ROLE_NAME_EXIST);
        }
        if(checkRoleSeqExist(param.getRoleSeq())){
            throw new AppException(AppParamEnum.ROLE_SEQ_EXIST);
        }
        return null;
    }

    private boolean checkRoleSeqExist(Integer roleSeq) {
        return true;
    }

    private boolean checkRoleNameExist(String roleName) {
        return true;
    }

    @Override
    public AdminRoleDto getById(Integer id) {
        return null;
    }

    @Override
    public Boolean update(AdminRoleParam param) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
