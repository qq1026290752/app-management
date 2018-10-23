package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import com.yulece.app.management.user.dto.DeptLevelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:AdminTreeService
 * @Package com.yulece.app.management.user.api
 * @Description:
 * @Date 创建时间 2018/10/23-22:37
 **/
@FeignClient(value = "app-management-user-provider",configuration = FeignHeaderInterceptor.class)
public interface AdminTreeService {

    @GetMapping("/dept/tree")
    List<DeptLevelDto> deptTree();
}
