package com.yulece.app.management.user.provide.rest.user;

import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminUserProvidersRest{

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/user/{id}")
    public AdminUserVo getById(@PathVariable("id") Integer id) {
        AdminUserVo userVo = adminUserService.getById(id);
        return userVo;
    }


    @PutMapping("/user/update")
    public boolean update(AdminUserParam param) {
        return false;
    }

    @PostMapping("/user/save")
    public boolean create(@RequestBody AdminUserParam param) {
        return adminUserService.create(param);
    }

    @GetMapping("/user/active")
    public boolean active(@RequestParam("key") String key) {
      return adminUserService.active(key);
    }
}
