package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminRoleService;
import com.yulece.app.management.user.dto.AdminRoleDto;
import com.yulece.app.management.user.entity.AdminRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminRoleController
 * @Package com.yulece.app.management.user.consumer.controller
 * @Description: 管理角色控制层
 * @Date 2019-03-10 13:01
 **/
@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @PostMapping
    public ResultVo<Boolean> save(@RequestBody AdminRoleParam param){
        return ResultVo.createSuccessResult(adminRoleService.save(param));
    }

    @GetMapping("/{id}")
    public ResultVo<AdminRoleDto> getById(@PathVariable Integer id){
        return ResultVo.createSuccessResult(adminRoleService.getById(id));
    }

    @PutMapping
    public ResultVo<Boolean> update(@RequestBody AdminRoleParam param){
        return ResultVo.createSuccessResult(adminRoleService.update(param));
    }

    @DeleteMapping("/{id}")
    public ResultVo<Boolean> delete(@PathVariable Integer id){
        return ResultVo.createSuccessResult(adminRoleService.delete(id));
    }




}
