package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminRoleService;
import com.yulece.app.management.user.dto.AdminRoleAclDto;
import com.yulece.app.management.user.dto.AdminRoleDto;
import com.yulece.app.management.user.param.AdminRoleAclParam;
import com.yulece.app.management.user.param.AdminRoleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
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

    @InitBinder("param")
    public void initBinderAdminRoleParam(WebDataBinder binder){
        binder.setFieldDefaultPrefix("param.");
    }


    @Autowired
    private AdminRoleService adminRoleService;

    @PostMapping
    public ResultVo<Boolean> save(@RequestBody AdminRoleParam param){
        return ResultVo.createSuccessResult(adminRoleService.save(param));
    }

    @GetMapping
    public ResultVo<AdminRoleDto> getById(@RequestParam Integer id){
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

    @GetMapping("/list")
    public ResultVo<Page> list(@ModelAttribute("param") AdminRoleParam param ){
        return ResultVo.createSuccessResult(adminRoleService.findAdminRoleByPage(param));
    }

    @PostMapping("/addAdminRoleAcl")
    public ResultVo<AdminRoleAclDto> addAdminRoleAcl(@RequestBody AdminRoleAclParam param){
        return ResultVo.createSuccessResult(adminRoleService.addAdminRoleAcl(param));
    }

}
