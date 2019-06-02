package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminDeptService;
import com.yulece.app.management.user.api.AdminTreeService;
import com.yulece.app.management.user.dto.DeptLevelDto;
import com.yulece.app.management.user.param.AdminDeptParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/dept")
@RestController
public class AdminDeptController {

    @Autowired
    private AdminDeptService adminDeptService;
    @Autowired
    private AdminTreeService adminTreeService;

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

    @GetMapping("/tree")
    public ResultVo<List<DeptLevelDto>> tree() {
        List<DeptLevelDto> deptLevelList = adminTreeService.deptTree();
        return ResultVo.createSuccessResult(deptLevelList);
    }
}
