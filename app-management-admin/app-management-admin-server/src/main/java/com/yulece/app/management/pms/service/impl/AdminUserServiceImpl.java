package com.yulece.app.management.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lambdaworks.crypto.SCryptUtil;
import com.yulece.app.management.commons.utils.IPUtils;
import com.yulece.app.management.commons.utils.PojoConvertUtil;
import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.pms.entity.AdminUser;
import com.yulece.app.management.pms.repository.AdminUserRepository;
import com.yulece.app.management.pms.service.AdminDeptService;
import com.yulece.app.management.pms.service.AdminUserService;
import com.yulece.app.management.pms.vo.user.AdminUserCreateRequest;
import com.yulece.app.management.pms.vo.user.AdminUserUpdateRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserServiceImpl
 * @Package com.yulece.app.management.pms.service.impl
 * @Description:
 * @Date 2019-12-28 22:08
 **/
@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final AdminDeptService adminDeptService;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public AdminUserServiceImpl(AdminUserRepository adminUserRepository, AdminDeptService adminDeptService, StringRedisTemplate stringRedisTemplate) {
        this.adminUserRepository = adminUserRepository;
        this.adminDeptService = adminDeptService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String save(AdminUserCreateRequest param, HttpServletRequest request) {
        //判断用户名是否存在
        if(isExistUserName(param,null)){
            throw new AppException(AppParamEnum.USER_USERNAME_EXIST_ERROR);
        }
        //判断手机号是是否存在
        if(isExistPhone(param,null)){
            throw new AppException(AppParamEnum.USER_PHONE_EXIST);
        }
        //判断邮箱是否存在
        if(isExistMail(param,null)){
            throw new AppException(AppParamEnum.USER_EMAIL_EXIST);
        }
        //判断部门是否存在
        if(isExistAdminDept(param.getUserDept())){
            throw new AppException(AppParamEnum.DEPT_NOT_EXIST);
        }
        //保存用户
        String userPassWord = SCryptUtil.scrypt(param.getPassWord(),32768,8,1);
        AdminUser adminUser = PojoConvertUtil.convertPojo(param, AdminUser.class);
        adminUser.setCreateTime(new Date());
        adminUser.setUpdateTime(new Date());
        adminUser.setOperateIp(IPUtils.getIp(request));
        adminUserRepository.insert(adminUser);
        //生成激活码 保存到缓存 三天
        String randomCode = RandomStringUtils.randomAlphanumeric(6);
        //发送激活邮件
        //返回用户邮箱
        return null;
    }

    private boolean isExistAdminDept(Integer deptId) {
        return ObjectUtils.isEmpty(adminDeptService.findOne(deptId));
    }

    @Override
    public void update(AdminUserUpdateRequest param, HttpServletRequest request) {
        if(isExistUserName(param,param.getUserId())){

        }
    }

    private boolean isExistUserName(AdminUserCreateRequest param, Integer userId) {
        return adminUserRepository.selectList(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getUserName,param.getUserName())
                .eq(userId!=null,AdminUser::getUserId,userId)).size()>0;
    }

    private boolean isExistPhone(AdminUserCreateRequest param, Integer userId) {
        return adminUserRepository.selectList(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getPhone,param.getUserName())
                .eq(userId!=null,AdminUser::getUserId,userId)).size()>0;
    }

    private boolean isExistMail(AdminUserCreateRequest param, Integer userId) {
        return adminUserRepository.selectList(new QueryWrapper<AdminUser>().lambda()
                .eq(AdminUser::getMail,param.getUserName())
                .eq(userId!=null,AdminUser::getUserId,userId)).size()>0;
    }
}
