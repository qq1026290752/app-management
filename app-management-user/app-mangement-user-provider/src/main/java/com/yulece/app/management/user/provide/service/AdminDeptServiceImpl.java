package com.yulece.app.management.user.provide.service;

import com.google.common.base.Preconditions;
import com.yulece.app.management.comments.api.interceptor.LoginHandlerInterceptor;
import com.yulece.app.management.commons.utils.BeanValidator;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminDeptService;
import com.yulece.app.management.user.entity.AdminDeptParam;
import com.yulece.app.management.user.provide.pojo.AdminDept;
import com.yulece.app.management.user.provide.repositories.AdminDeptRepository;
import com.yulece.app.management.user.provide.utils.LevelUtil;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:DeptServiceImpl
 * @Package com.yulece.app.management.user.provide.service
 * @Description:
 * @Date 创建时间 2018/10/21-21:27
 **/
@Service
public class AdminDeptServiceImpl implements AdminDeptService {

    @Autowired
    private AdminDeptRepository adminDeptRepository;


    @Override
    public Boolean save(AdminDeptParam param) {
        checkUser(param);
        AdminDept adminDept = PojoConvertUtil.convertPojo(param, AdminDept.class);
        adminDept.setDeptLevel(LevelUtil.calculateLevel(getLevel(adminDept.getDeptParentId()), adminDept.getDeptParentId()));
       return adminDeptRepository.insert(adminDept) > 0;
    }

    @Override
    public Boolean update(AdminDeptParam param) {
        checkUser(param);
        AdminDept beforeDept = adminDeptRepository.selectByPrimaryKey(param.getDeptId());
        Preconditions.checkNotNull(beforeDept, "该更新部门不存在");
        AdminDept adminDept = PojoConvertUtil.convertPojo(param, AdminDept.class);
        adminDept.setDeptLevel(LevelUtil.calculateLevel(getLevel(adminDept.getDeptParentId()), adminDept.getDeptParentId()));
        adminDept.setOperator(LoginHandlerInterceptor.getCurrentUser());
        adminDept.setOperateIp(LoginHandlerInterceptor.getCurrentIp());
        return updateWithChild(beforeDept, adminDept);
    }

    private void checkUser(AdminDeptParam param) {
        BeanValidator.check(param);
        if (existDeptName(param.getDeptName(), param.getDeptParentId(), param.getDeptId())) {
            throw new AppException(AppParamEnum.DEPT_NAME_EXIST_ERROR);
        }
        if (existDeptSeq(param.getDeptSeq(), param.getDeptParentId(), param.getDeptId())) {
            throw new AppException(AppParamEnum.DEPT_SEQ_EXIST_ERROR);
        }
    }

    @Override
    public Boolean delete(Integer deptId) {
        //查询是否存在子部门
        AdminDept beforeDept = adminDeptRepository.selectByPrimaryKey(deptId);
        Preconditions.checkNotNull(beforeDept, "需要删除的部门不存在");
        Integer deptCount = adminDeptRepository.findAdminDeptByParentId(deptId);
        if (deptCount > 0) {
            throw new AppException(AppParamEnum.DEPT_EXIST_DEPT);
        }
        Integer userCount = adminDeptRepository.findUserCountByDeptId(deptId);
        if (userCount > 0) {
            throw new AppException(AppParamEnum.DEPT_EXIST_USER);
        }
        int count = adminDeptRepository.deleteByPrimaryKey(deptId);
        return count > 0;
    }


    @Transactional
    public Boolean updateWithChild(AdminDept before, AdminDept after) {
        String newLevelPrefix = after.getDeptLevel();
        String oldLevelPrefix = before.getDeptLevel();
        if (!StringUtils.equals(newLevelPrefix, oldLevelPrefix)) {
            List<AdminDept> adminDepts = adminDeptRepository.getDeptChildListByLevel(before.getDeptLevel());
            //判断集合是否为空
            if (!CollectionUtils.isEmpty(adminDepts)) {
                for (AdminDept dept : adminDepts) {
                    //取出当前的等级
                    String level = dept.getDeptLevel();
                    //判断当前lavel 是否存在于old
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        //更新当前lavel
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setDeptLevel(level);
                        adminDeptRepository.updateByPrimaryKey(dept);
                    }
                }
            }
        }
        //更新部门
        return adminDeptRepository.updateByPrimaryKeySelective(after) > 0 ;
    }

    private boolean existDeptName(String deptName, Integer parentId, Integer deptId) {
        AdminDeptParam adminDeptParam = new AdminDeptParam();
        adminDeptParam.setDeptId(deptId);
        adminDeptParam.setDeptName(deptName);
        adminDeptParam.setDeptParentId(parentId);
        return adminDeptRepository.existDeptByDept(adminDeptParam) > 0;
    }

    private boolean existDeptSeq(Integer seq, Integer parentId, Integer deptId) {
        AdminDeptParam adminDeptParam = new AdminDeptParam();
        adminDeptParam.setDeptId(deptId);
        adminDeptParam.setDeptSeq(seq);
        adminDeptParam.setDeptParentId(parentId);
        return adminDeptRepository.existDeptByDept(adminDeptParam) > 0;
    }

    private String getLevel(Integer deptId) {
        AdminDept adminDept = adminDeptRepository.selectByPrimaryKey(deptId);
        if (adminDept == null) {
            return null;
        }
        return adminDept.getDeptLevel();
    }
}
