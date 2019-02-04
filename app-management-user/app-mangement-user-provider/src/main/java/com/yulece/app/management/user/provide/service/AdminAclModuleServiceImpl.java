package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminAclModuleService;
import com.yulece.app.management.user.dto.AdminAclModuleDto;
import com.yulece.app.management.user.entity.AdminAclModuleParam;
import com.yulece.app.management.user.provide.pojo.AdminAclModule;
import com.yulece.app.management.user.provide.repositories.AdminAclModuleRepository;
import com.yulece.app.management.user.provide.utils.LevelUtil;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleServiceImpl
 * @Package com.yulece.app.management.user.provide.service
 * @Description:
 * @Date 2019-01-20 19:30
 **/
@Service
public class AdminAclModuleServiceImpl implements AdminAclModuleService {

    @Autowired
    private AdminAclModuleRepository adminAclModuleRepository;

    @Override
    public Integer save(AdminAclModuleParam param) {
        checkAdminAclModule(param);
        //查询父模块
        AdminAclModule adminAclModule = getAdminAclModule(param);
        adminAclModule.setOperateIp(LoginHandlerInterceptor.getCurrentIp());
        adminAclModule.setOperator(LoginHandlerInterceptor.getCurrentUser());
        adminAclModuleRepository.insertSelective(adminAclModule);
        return adminAclModule.getModuleId();
    }

    private AdminAclModule getAdminAclModule(AdminAclModuleParam param) {
        AdminAclModule queryCondition = new AdminAclModule();
        queryCondition.setModuleId(param.getModuleParentId());
        queryCondition.setStatus(0);
        AdminAclModule parentAclModule = adminAclModuleRepository.selectOne(queryCondition);
        BeanValidator.chekObjectNull(parentAclModule, AppParamEnum.ACL_MODULE_NOT_EXIST);
        AdminAclModule adminAclModule = PojoConvertUtil.convertPojo(param, AdminAclModule.class);
        adminAclModule.setModuleLevel(LevelUtil.calculateLevel(parentAclModule.getModuleLevel(),parentAclModule.getModuleId()));
        return adminAclModule;
    }

    private void checkAdminAclModule(AdminAclModuleParam param) {
        BeanValidator.check(param);
        //查询该分组下是否存在该模块
        if(existAclModuleName(param)){
            throw new AppException(AppParamEnum.ACL_MODULE_NAME_EXIST);
        }
        //查询模块序号是否重复
        if(existAclModuleSeq(param)){
            throw new AppException(AppParamEnum.ACL_MODULE_SEQ_EXIST);
        }


    }

    private boolean existAclModuleSeq(AdminAclModuleParam param) {
        return adminAclModuleRepository.countAclModuleSeq(param.getModuleId(),param.getModuleParentId(),param.getModuleSeq()) > 0;
    }

    private boolean existAclModuleName(AdminAclModuleParam param) {
        return adminAclModuleRepository.countAclModuleName(param.getModuleId(),param.getModuleParentId(),param.getModuleName()) > 0;
    }

    @Override
    public Integer update(AdminAclModuleParam param) {
        checkAdminAclModule(param);
        //查询更新的权限模块是否存在
        AdminAclModule afterAclModule = adminAclModuleRepository.selectByPrimaryKey(param.getModuleId());
        BeanValidator.chekObjectNull(afterAclModule,AppParamEnum.ACL_MODULE_NOT_EXIST);
        AdminAclModule beforeAclModule = getAdminAclModule(param);

        return updateWithChild(afterAclModule,beforeAclModule);
    }
    @Transactional
    public Integer updateWithChild(AdminAclModule afterAclModule, AdminAclModule beforeAclModule) {
        if(!afterAclModule.getModuleLevel().equals(beforeAclModule.getModuleLevel())){
            //查询是否存在子目录
           List<AdminAclModule> adminAclModuleList =  adminAclModuleRepository.findChildModule(afterAclModule.getModuleLevel());
           if(CollectionUtils.isNotEmpty(adminAclModuleList)){
               for (AdminAclModule adminAclModule : adminAclModuleList) {
                   //获取当前目录的等级
                   String level = adminAclModule.getModuleLevel();
                   if(level.indexOf(afterAclModule.getModuleLevel())!=-1){
                       String newAclModule = beforeAclModule.getModuleLevel() + level.substring(adminAclModule.getModuleLevel().length());
                       adminAclModule.setModuleLevel(newAclModule);
                       adminAclModuleRepository.updateByPrimaryKey(adminAclModule);
                   }
               }
           }
        }
        adminAclModuleRepository.updateByPrimaryKey(beforeAclModule);
        return  afterAclModule.getModuleId();
    }

    @Override
    public Integer delete(Integer aclModelId) {
        //查询是否存在子模块
       Integer count =  adminAclModuleRepository.existAclModuleCount(aclModelId);
       if(count > 0){
           throw new AppException(AppParamEnum.ACL_MODULE_SEQ_EXIST_ACL);
       }
       return adminAclModuleRepository.deleteByPrimaryKey(aclModelId);
    }

    @Override
    public AdminAclModuleDto getAdminAclModelById(Integer aclModelId) {
        AdminAclModule adminAclModule = adminAclModuleRepository.selectByPrimaryKey(aclModelId);
        BeanValidator.chekObjectNull(adminAclModule, AppParamEnum.ACL_MODULE_NOT_EXIST);
        return PojoConvertUtil.convertPojo(adminAclModule, AdminAclModuleDto.class);
    }
}
