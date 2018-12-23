package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminAclService;
import com.yulece.app.management.user.dto.AdminAclDto;
import com.yulece.app.management.user.entity.AdminAclParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclController
 * @Package com.yulece.app.management.user.consumer.controller
 * @Description: 权限点控制层
 * @Date 2018-12-23 09:39
 **/
@RestController
@RequestMapping("/aclAdmin")
public class AdminAclController {

    @Autowired
    private AdminAclService adminAclService;

    @PostMapping()
    public ResultVo<Integer> save(@RequestBody AdminAclParam param){
        return ResultVo.createErrorResult(adminAclService.addAdminAcl(param));
    }

    @PutMapping()
    public ResultVo<Integer> update(@RequestBody AdminAclParam param){
        return ResultVo.createErrorResult(adminAclService.updateAdminAcl(param));
    }

    @DeleteMapping("/{id}")
    public ResultVo<Integer> delete(@PathVariable("id")Integer aclId){
        return ResultVo.createErrorResult(adminAclService.deleteByAclId(aclId));
    }

    @GetMapping("/{id}")
    public ResultVo<AdminAclDto> findOne(@PathVariable("id")Integer aclId){
        return ResultVo.createErrorResult(adminAclService.getByAclId(aclId));
    }
}
