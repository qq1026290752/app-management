package com.yulece.app.management.user.provide.rest.user;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserDto;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.param.AdminUserParam;
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


}
