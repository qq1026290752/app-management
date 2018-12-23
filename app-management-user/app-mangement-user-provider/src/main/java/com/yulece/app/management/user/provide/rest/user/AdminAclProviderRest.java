package com.yulece.app.management.user.provide.rest.user;

import com.yulece.app.management.user.api.AdminAclService;
import com.yulece.app.management.user.dto.AdminAclDto;
import com.yulece.app.management.user.entity.AdminAclParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclProviderRest
 * @Package com.yulece.app.management.user.provide
 * @Description:
 * @Date 2018-12-22 16:03
 **/
@RestController
public class AdminAclProviderRest {

    @Autowired
    private AdminAclService adminAclService;


    //增加权限点
    @PostMapping
    public Integer addAdminAcl(@RequestBody AdminAclParam adminAclParam){
        return adminAclService.addAdminAcl(adminAclParam);
    }
    //修改权限点
    @PutMapping
    public Integer updateAdminAcl(@RequestBody AdminAclParam adminAclParam){
        return adminAclService.updateAdminAcl(adminAclParam);
    }
    //删除权限点
    @DeleteMapping("/{id}")
    public Integer deleteByAclId(@PathVariable("id") Integer aclId){
        return adminAclService.deleteByAclId(aclId);
    }
    @GetMapping("/{id}")
    public AdminAclDto getByAclId(@PathVariable("id") Integer aclId){
        return adminAclService.getByAclId(aclId);
    }
}
