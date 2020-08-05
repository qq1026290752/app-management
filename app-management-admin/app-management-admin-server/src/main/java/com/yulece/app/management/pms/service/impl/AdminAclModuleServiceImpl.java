package com.yulece.app.management.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.IPUtils;
import com.yulece.app.management.commons.utils.PojoConvertUtil;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.pms.dto.acl.module.AdminAclModuleResponse;
import com.yulece.app.management.pms.entity.AdminAclModule;
import com.yulece.app.management.pms.entity.tree.AdminAclModuleTreeDto;
import com.yulece.app.management.pms.repository.AdminAclModuleRepository;
import com.yulece.app.management.pms.service.AdminAclModuleService;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleCreateRequest;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleQueryRequest;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleServiceImpl
 * @Package com.yulece.app.management.pms.service.impl
 * @Description:
 * @Date 2020-01-05 20:03
 **/
@Service
public class AdminAclModuleServiceImpl implements AdminAclModuleService {

    private final AdminAclModuleRepository adminAclModuleRepository;

    @Autowired
    public AdminAclModuleServiceImpl(AdminAclModuleRepository adminAclModuleRepository) {
        this.adminAclModuleRepository = adminAclModuleRepository;
    }

    @Override
    public Integer save(HttpServletRequest request, AdminAclModuleCreateRequest param) {
        BeanValidator.check(param);
        if(isExistAclModuleName(param.getModuleName(),param.getModuleParentId(),null)){
            throw new AppException(AppParamEnum.ACL_MODULE_NAME_EXIST);
        }
        if(isExistAclSeq(param.getModuleSeq(),param.getModuleParentId(),null)){
            throw new AppException(AppParamEnum.ACL_MODULE_SEQ_EXIST);
        }
        AdminAclModule parentAclModule = adminAclModuleRepository.selectById(param.getModuleParentId());
        AdminAclModule adminAclModule = PojoConvertUtil.convertPojo(param, AdminAclModule.class);
        adminAclModule.setModuleLevel(AdminAclModule.getLevel(parentAclModule));
        adminAclModule.setCreateTime(new Date());
        adminAclModule.setOperateIp(IPUtils.getIp(request));
        adminAclModule.setUpdateTime(new Date());
        adminAclModuleRepository.insert(adminAclModule);
        return adminAclModule.getModuleId();
    }

    private boolean isExistAclModuleName(String moduleName, Integer moduleParentId, Integer aclModuleId) {
        return adminAclModuleRepository.selectList(
                new LambdaQueryWrapper<AdminAclModule>()
                .eq(AdminAclModule::getModuleName,moduleName)
                .eq(AdminAclModule::getModuleParentId,moduleParentId)
                .ne(aclModuleId!=null,AdminAclModule::getModuleId,aclModuleId)
        ).size()>0;
    }

    private boolean isExistAclSeq(Integer aclModuleSeq, Integer moduleParentId, Integer aclModuleId) {
        return adminAclModuleRepository.selectList(
                new LambdaQueryWrapper<AdminAclModule>()
                        .eq(AdminAclModule::getModuleSeq,aclModuleSeq)
                        .eq(AdminAclModule::getModuleParentId,moduleParentId)
                        .ne(aclModuleId!=null,AdminAclModule::getModuleId,aclModuleId)
        ).size()>0;
    }

    @Override
    @Transactional
    public Integer update(HttpServletRequest request, AdminAclModuleUpdateRequest param) {
        BeanValidator.check(param);
        if(isExistAclModuleName(param.getModuleName(),param.getModuleParentId(),null)){
            throw new AppException(AppParamEnum.ACL_MODULE_NAME_EXIST);
        }
        if(isExistAclSeq(param.getModuleSeq(),param.getModuleParentId(),null)){
            throw new AppException(AppParamEnum.ACL_MODULE_SEQ_EXIST);
        }
        AdminAclModule oldAclModule = adminAclModuleRepository.selectById(param.getAclModuleId());
        BeanValidator.checkObjectNull(oldAclModule,AppParamEnum.ACL_MODULE_NOT_EXIST);
        String oldAclModuleLevel = oldAclModule.getModuleLevel();
        if(!param.getModuleParentId().equals(oldAclModule.getModuleParentId())){
            AdminAclModule parentAclModule = adminAclModuleRepository.selectById(param.getModuleParentId());
            oldAclModule.setModuleLevel(AdminAclModule.getLevel(parentAclModule));
            List<AdminAclModule> adminAclModules = adminAclModuleRepository.selectList(new QueryWrapper<AdminAclModule>().lambda()
                    .likeRight(AdminAclModule::getModuleLevel, oldAclModule.getModuleLevel())
            );
            for (AdminAclModule adminAclModule : adminAclModules) {
                adminAclModule.setModuleLevel(oldAclModule.getModuleLevel() + "," + adminAclModule.getModuleLevel().substring(oldAclModuleLevel.length()));
                adminAclModuleRepository.updateById(adminAclModule);
            }
        }
        adminAclModuleRepository.updateById(oldAclModule);
        return oldAclModule.getModuleId();
    }

    @Override
    public IPage<AdminAclModuleResponse> page(AdminAclModuleQueryRequest param) {
        return adminAclModuleRepository.findAllByPageAndObject(new Page<AdminAclModuleResponse>(param.getPageNo(),param.getPageSize()),param);
    }

    @Override
    public AdminAclModuleResponse findOne(Integer id) {
        return PojoConvertUtil.convertPojo(adminAclModuleRepository.selectById(id),AdminAclModuleResponse.class);
    }

    @Override
    public List<AdminAclModuleTreeDto> trees() {
        List<AdminAclModule> adminAclModules = adminAclModuleRepository.selectList(null);
        List<AdminAclModuleTreeDto> dtoList = Lists.newArrayList();
        for (AdminAclModule aclModule : adminAclModules) {
            dtoList.add(AdminAclModuleTreeDto.adapt(aclModule));
        }
        return aclModuleListToTree(dtoList);
    }

    private List<AdminAclModuleTreeDto> aclModuleListToTree(List<AdminAclModuleTreeDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [aclmodule1, aclmodule2, ...] Map<String, List<Object>>
        Multimap<String, AdminAclModuleTreeDto> levelAclModuleMap = ArrayListMultimap.create();
        List<AdminAclModuleTreeDto> rootList = Lists.newArrayList();

        for (AdminAclModuleTreeDto dto : dtoList) {
            levelAclModuleMap.put(dto.getModuleLevel(), dto);
            if (AdminAclModule.LEVEL.equals(dto.getModuleLevel())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, aclModuleSeqComparator);
        transformAclModuleTree(rootList, AdminAclModule.LEVEL, levelAclModuleMap);
        return rootList;
    }

    private void transformAclModuleTree(List<AdminAclModuleTreeDto> rootList, String level, Multimap<String, AdminAclModuleTreeDto> levelAclModuleMap) {
        for (int i = 0; i < rootList.size(); i++) {
            AdminAclModuleTreeDto dto = rootList.get(i);
            String nextLevel = AdminAclModule.getLevel(level, dto.getModuleId());
            List<AdminAclModuleTreeDto> tempList = (List<AdminAclModuleTreeDto>) levelAclModuleMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempList)) {
                Collections.sort(tempList, aclModuleSeqComparator);
                dto.setAclModules(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }

    public Comparator<AdminAclModuleTreeDto> aclModuleSeqComparator =  (o1,o2)->  {
            return o1.getModuleSeq() - o2.getModuleSeq();
    };
}
