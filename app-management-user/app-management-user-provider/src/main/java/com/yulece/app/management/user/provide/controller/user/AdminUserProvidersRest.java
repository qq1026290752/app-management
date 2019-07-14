package com.yulece.app.management.user.provide.controller.user;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.user.api.AdminRoleUserService;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserDto;
import com.yulece.app.management.user.dto.AdminUserRoleDto;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.param.AdminUserParam;
import com.yulece.app.management.user.param.AdminUserRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminUserProvidersRest{

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoleUserService adminRoleUserService;

    @GetMapping("/user/{id}")
    public AdminUserVo getById(@PathVariable("id") Integer id) {
        AdminUserVo userVo = adminUserService.getById(id);
        return userVo;
    }


    @PutMapping("/user/update")
    public boolean update(@RequestBody AdminUserParam param) {
        return adminUserService.update(param);
    }

    @PostMapping("/user/save")
    public boolean create(@RequestBody AdminUserParam param) {
        return adminUserService.create(param);
    }

    @GetMapping("/user/active")
    public boolean active(@RequestParam("key") String key) {
      return adminUserService.active(key);
    }

    @PostMapping("/user/list")
    private Page<AdminUserDto> getlList(@RequestBody AdminUserParam param){
        return adminUserService.getList(param);
    }
    @PostMapping("/user/addUserByRole")
    private AdminUserRoleDto addUserByRole(@RequestBody AdminUserRoleParam param){
        return adminRoleUserService.addUserByRole(param);
    }

    @GetMapping("/user/getUserRole")
    private AdminUserRoleDto getUserRole(@RequestParam("userId") Integer userId){
        return adminRoleUserService.findUserRole(userId);
    }

}
