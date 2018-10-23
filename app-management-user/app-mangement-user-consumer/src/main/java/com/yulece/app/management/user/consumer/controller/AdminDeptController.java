package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminDeptService;
import com.yulece.app.management.user.entity.AdminDeptParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:AdminDeptController
 * @Package com.yulece.app.management.user.consumer.controller
 * @Description:部门控制层开发
 * @Date 创建时间 2018/10/22-12:08
 **/
@RequestMapping("/admin/dept")
@RestController
public class AdminDeptController {

    @Autowired
    private AdminDeptService adminDeptService;

    @PostMapping
    public ResultVo create(@RequestBody AdminDeptParam param){
        Boolean success = adminDeptService.save(param);
        return success ? ResultVo.createSuccessResult():ResultVo.createErrorResult();
    }

    @PutMapping
    public ResultVo update(@RequestBody AdminDeptParam param){
        Boolean success = adminDeptService.update(param);
        return success ? ResultVo.createSuccessResult():ResultVo.createErrorResult();
    }

    @DeleteMapping("/{deptId}")
    public ResultVo delete(@PathVariable("deptId") Integer deptId){
        Boolean success = adminDeptService.delete(deptId);
        return success ? ResultVo.createSuccessResult():ResultVo.createErrorResult();
    }
}
