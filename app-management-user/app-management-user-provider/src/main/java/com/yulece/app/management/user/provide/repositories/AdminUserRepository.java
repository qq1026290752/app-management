package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.user.dto.AdminUserDto;
import com.yulece.app.management.user.param.AdminUserParam;
import com.yulece.app.management.user.provide.pojo.AdminUser;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserRepository extends AppUserProviderMapper<AdminUser> {
    AdminUser findOne(Integer id);

    Integer countEmailByUserIdAndMail(@Param("userId") Integer userId,@Param("mail") String mail);

    Integer countUserByUserParam(AdminUserParam adminUserParam);

    AdminUser findAdminUserByMail(String eMail);

    List<AdminUserDto> findListByAdminUserParam(Page<AdminUserDto> page, AdminUserParam param);
}
