package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import com.yulece.app.management.user.entity.AdminDeptParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:AdminDeptService
 * @Package com.yulece.app.management.user.api
 * @Description: 部门管理service
 * @Date 创建时间 2018/10/21-21:17
 **/
@FeignClient(value = "app-management-user-provider")
@RequestMapping("/dept")
public interface AdminDeptService {

    @PostMapping("/save")
    Boolean save(@RequestBody AdminDeptParam param);

    @PutMapping("/update")
    Boolean update(@RequestBody AdminDeptParam param);

    @DeleteMapping("/delete")
    Boolean delete(Integer deptId);
}
