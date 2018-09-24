package com.yulece.app.management.user.provide.rest.user;

import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserProvidersRest implements AdminUserService {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    @GetMapping("/user/{id}")
    public AdminUserVo getById(@PathVariable("id") Integer id) {
        AdminUserVo userVo = adminUserService.getById(id);
        return userVo;
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
