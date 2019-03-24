package com.yulece.app.management.user.provide.rest.user;

import com.yulece.app.management.user.api.AdminRoleService;
import com.yulece.app.management.user.dto.AdminRoleDto;
import com.yulece.app.management.user.entity.AdminRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleProviderRest
 * @Package com.yulece.app.management.user.provide.rest.user
 * @Description:
 * @Date 2019-03-17 13:33
 **/
@RestController
@RequestMapping("/role")
public class AdminRoleProviderRest  {

    @Autowired
    private AdminRoleService adminRoleService;
    @PostMapping("/role/save")
    public Boolean save(@RequestBody AdminRoleParam param){
        return adminRoleService.save(param);
    }

    @GetMapping("/role/{id}")
    public AdminRoleDto getById(@PathVariable("id") Integer id){
        return adminRoleService.getById(id);
    }

    @PutMapping("/role/update" )
    public Boolean update(@RequestBody AdminRoleParam param){
        return adminRoleService.update(param);
    }

    @DeleteMapping("/role/{id}")
    public Boolean delete(@PathVariable("id") Integer id){
        return  adminRoleService.delete(id);
    }




}