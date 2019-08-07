package com.yulece.app.management.user.provide.controller.user;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.user.api.AdminAclModuleService;
import com.yulece.app.management.user.dto.AdminAclModuleDto;
import com.yulece.app.management.user.param.AdminAclModuleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleProviderRest
 * @Package com.yulece.app.management.user.provide.rest.user
 * @Description:
 * @Date 2019-01-20 18:11
 **/
@RestController
@RequestMapping("/aclModule")
public class AdminAclModuleProviderRest {

    @Autowired
    private AdminAclModuleService adminAclModuleService;

    @PostMapping
    public Integer save(AdminAclModuleParam param){
        return adminAclModuleService.save(param);
    }


    /**
     * 修改权限模块
     * @param param
     * @return
     */
    @PutMapping
    public Integer update(AdminAclModuleParam param){
        return adminAclModuleService.update(param);
    }

    /**
     * 删除权限模块
     * @param aclModuleId 模块ID
     * @return 删除的模块ID
     */
    @DeleteMapping("/{aclModuleId}")
    public Integer delete(@PathVariable("aclModuleId")Integer aclModuleId){
        return adminAclModuleService.delete(aclModuleId);
    }

    /**
     * 根据权限模块ID查询权限模块
     * @param aclModuleId
     * @return
     */
    @GetMapping("/{aclModuleId}")
    public AdminAclModuleDto getAdminAclModelById(@PathVariable("aclModuleId")Integer aclModuleId){
        return adminAclModuleService.getAdminAclModelById(aclModuleId);
    }

    @PostMapping("/list")
    public Page<AdminAclModuleDto> getList(@RequestBody AdminAclModuleParam param){
        return adminAclModuleService.getList(param);
    }
}
