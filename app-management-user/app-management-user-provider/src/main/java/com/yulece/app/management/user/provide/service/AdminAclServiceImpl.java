package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminAclService;
import com.yulece.app.management.user.dto.AdminAclDto;
import com.yulece.app.management.user.param.AdminAclParam;
import com.yulece.app.management.user.provide.pojo.AdminAcl;
import com.yulece.app.management.user.provide.repositories.AdminAclRepository;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclServiceImpl
 * @Package com.yulece.app.management.user.provide.service
 * @Description:
 * @Date 2018-12-22 16:17
 **/
@Service
public class AdminAclServiceImpl implements AdminAclService {

    @Autowired
    private AdminAclRepository adminAclRepository;

    @Override
    public Integer addAdminAcl(AdminAclParam adminAclParam) {
        //校验权限点
        checkAcl(adminAclParam);
        AdminAcl adminAcl = PojoConvertUtil.convertPojo(adminAclParam, AdminAcl.class);
        adminAcl.setCreateTime(new Date());
        adminAcl.setUpdateTime(adminAcl.getCreateTime());
        adminAcl.setOperator(LoginHandlerInterceptor.getCurrentUser());
        adminAcl.setOperateIp(LoginHandlerInterceptor.getCurrentIp());
        adminAclRepository.insert(adminAcl);
        return adminAcl.getAclId();
    }

    private void checkAcl(AdminAclParam adminAclParam) {
        BeanValidator.check(adminAclParam);
        //查询权限点是否存在
        if (findAdminAclNameIsExistByAdminAcl(adminAclParam.getAclId(), adminAclParam.getAclName(), adminAclParam.getAclModuleId())) {
            throw new AppException(AppParamEnum.ACL_ACL_NAME_EXIST_ERROR);
        }
        //查询权限点排序是否存在
        if (findAdminAclSEQIsExistByAdminAcl(adminAclParam.getAclId(), adminAclParam.getAclSeq(), adminAclParam.getAclModuleId())) {
            throw new AppException(AppParamEnum.ACL_ACL_SEQ_EXIST_ERROR);
        }
    }

    @Override
    public Integer updateAdminAcl(AdminAclParam adminAclParam) {
        //根据权限点ID查询 权限点是否存在
        AdminAcl adminAcl = adminAclRepository.selectByPrimaryKey(adminAclParam.getAclId());
        BeanValidator.checkObjectNull(adminAcl,AppParamEnum.ACL_ACL_NULL_EXIST);
        //检查参数是否正确
        checkAcl(adminAclParam);
        adminAcl = PojoConvertUtil.convertPojo(adminAclParam, AdminAcl.class);
        adminAcl.setUpdateTime(new Date());
        adminAclRepository.updateByPrimaryKeySelective(adminAcl);
        return adminAcl.getAclId();
    }

    @Override
    public Integer deleteByAclId(Integer aclId) {
        return adminAclRepository.deleteByPrimaryKey(aclId);
    }

    @Override
    public AdminAclDto getByAclId(Integer aclId) {
        AdminAcl adminAcl = adminAclRepository.selectByPrimaryKey(aclId);
        return PojoConvertUtil.convertPojo(adminAcl,AdminAclDto.class);
    }

    private boolean findAdminAclNameIsExistByAdminAcl(Integer aclId,String aclName,Integer aclModelId){
        AdminAcl adminAcl = new AdminAcl();
        adminAcl.setAclId(aclId);
        adminAcl.setAclName(aclName);
        adminAcl.setAclModuleId(aclModelId);
        return adminAclRepository.findAdminAclIsExistByAdminAcl(adminAcl) > 0 ;
    }

    private boolean findAdminAclSEQIsExistByAdminAcl(Integer aclId,Integer aclSEQ,Integer aclModelId){
        AdminAcl adminAcl = new AdminAcl();
        adminAcl.setAclId(aclId);
        adminAcl.setAclSeq(aclSEQ);
        adminAcl.setAclModuleId(aclModelId);
        return adminAclRepository.findAdminAclIsExistByAdminAcl(adminAcl) > 0 ;
    }
}
