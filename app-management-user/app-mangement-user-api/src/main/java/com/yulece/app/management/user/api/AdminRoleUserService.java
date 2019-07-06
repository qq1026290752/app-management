package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import com.yulece.app.management.user.dto.AdminUserRoleDto;
import com.yulece.app.management.user.param.AdminUserRoleParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleUserService
 * @Package com.yulece.app.management.user.api
 * @Description:
 * @Date 2019-06-02 17:00
 **/

@FeignClient(value = "app-management-user-provider", configuration = FeignHeaderInterceptor.class)
public interface AdminRoleUserService {

    @PostMapping("/user/addUserByRole")
    AdminUserRoleDto addUserByRole(AdminUserRoleParam param);
    @GetMapping("/user/getUserRole")
    AdminUserRoleDto findUserRole(@RequestParam("userId") Integer userId);
}
