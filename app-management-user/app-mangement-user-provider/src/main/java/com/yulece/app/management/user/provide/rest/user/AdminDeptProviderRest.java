package com.yulece.app.management.user.provide.rest.user;

import com.yulece.app.management.user.api.AdminDeptService;
import com.yulece.app.management.user.api.AdminTreeService;
import com.yulece.app.management.user.dto.DeptLevelDto;
import com.yulece.app.management.user.entity.AdminDeptParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:AdminDeptProviderRest
 * @Package com.yulece.app.management.user.provide.rest.user
 * @Description:
 * @Date 创建时间 2018/10/23-22:48
 **/
@RestController
public class AdminDeptProviderRest{

    @Autowired
    private AdminDeptService adminDeptService;

    @Autowired
    private AdminTreeService adminTreeService;

    @PostMapping("/dept/save")
    public Boolean save(@RequestBody AdminDeptParam param) {
        return adminDeptService.save(param);
    }

    @PutMapping("/dept/update")
    public Boolean update(@RequestBody AdminDeptParam param) {
        return adminDeptService.update(param);
    }

    @DeleteMapping("/dept/{deptId}")
    public Boolean delete(@PathVariable("deptId") Integer deptId) {
        return adminDeptService.delete(deptId);
    }
    @GetMapping("/dept/tree")
    public List<DeptLevelDto> tree() {
        return adminTreeService.deptTree();
    }
}
