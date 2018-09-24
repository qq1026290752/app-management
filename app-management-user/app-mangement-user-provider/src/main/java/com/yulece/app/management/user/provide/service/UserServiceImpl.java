package com.yulece.app.management.user.provide.service;

import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import com.yulece.app.management.user.provide.pojo.AdminUser;
import com.yulece.app.management.user.provide.repositories.AdminUserRepository;
import com.yulece.app.management.user.provide.utils.PojoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AdminUserService {

    private final AdminUserRepository adminUserRepository;

    @Autowired
    public UserServiceImpl(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public AdminUserVo getById(Integer id) {
        AdminUser adminUser = adminUserRepository.findOne(id);
        AdminUserVo adminUserVo = PojoConvertUtil.convertPojo(adminUser, AdminUserVo.class);
        return adminUserVo;
    }

    @Override
    public boolean update(AdminUserParam param) {
        return false;
    }

    @Override
    public boolean create(AdminUserParam param) {
        return false;
    }

    @Override
    public void active(String key) {

    }
}
