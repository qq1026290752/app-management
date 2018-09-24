package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.user.provide.pojo.AdminUser;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;

public interface AdminUserRepository extends AppUserProviderMapper<AdminUser> {
    AdminUser findOne(Integer id);
}
