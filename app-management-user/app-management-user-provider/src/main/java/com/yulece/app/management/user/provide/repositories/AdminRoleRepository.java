package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.user.dto.AdminRoleDto;
import com.yulece.app.management.user.param.AdminRoleParam;
import com.yulece.app.management.user.provide.pojo.AdminRole;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;

import java.util.List;

public interface AdminRoleRepository extends AppUserProviderMapper<AdminRole> {

    List<AdminRole> getAdminRoleByAdminRole(AdminRole adminRole);


    List<AdminRoleDto> findAdminRoleByPage(Page page, AdminRoleParam param);
}
