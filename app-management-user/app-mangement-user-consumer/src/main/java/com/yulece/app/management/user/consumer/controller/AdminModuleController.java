package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminAclModuleService;
import com.yulece.app.management.user.api.AdminTreeService;
import com.yulece.app.management.user.dto.AdminAclModuleDto;
import com.yulece.app.management.user.entity.AdminAclModuleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminModuleController
 * @Package com.yulece.app.management.user.consumer.controller
 * @Description:权限模块控制层
 * @Date 2019-01-18 08:39
 **/
@RestController
@RequestMapping("/adminAclModule")
public class AdminModuleController {

    @Autowired
    private AdminAclModuleService adminAclModelService;
    @Autowired
    private AdminTreeService adminTreeService;

    @PostMapping
    public ResultVo<Integer> save(@RequestBody AdminAclModuleParam param){
        Integer saveId = adminAclModelService.save(param);
        return ResultVo.createSuccessResult(saveId);
    }
    @GetMapping("/{id}")
    public ResultVo<AdminAclModuleDto> getAdminAclModelDtoById(@PathVariable("id") Integer id){
        return ResultVo.createSuccessResult(adminAclModelService.getAdminAclModelById(id));
    }
    @DeleteMapping("/{id}")
    public ResultVo<Integer> deleteById(@PathVariable("id") Integer id){
        Integer result = adminAclModelService.delete(id);
        return ResultVo.createErrorResult(result);
    }

    @PutMapping
    public ResultVo<Integer> update(@RequestBody AdminAclModuleParam param){
        return ResultVo.createSuccessResult(adminAclModelService.update(param));
    }

    @GetMapping("/tree")
    public ResultVo<List<AdminAclModuleDto>> tree(){
        return ResultVo.createSuccessResult(adminTreeService.adminModuleTree());
    }
}
