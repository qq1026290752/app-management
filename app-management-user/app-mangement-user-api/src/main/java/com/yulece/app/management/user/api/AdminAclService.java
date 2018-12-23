package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import com.yulece.app.management.user.dto.AdminAclDto;
import com.yulece.app.management.user.entity.AdminAclParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "app-management-user-provider",configuration = FeignHeaderInterceptor.class)
@RequestMapping("/acl")
public interface AdminAclService {

    //增加权限点
    @PostMapping
    Integer addAdminAcl(@RequestBody AdminAclParam adminAclParam);
    //修改权限点
    @PutMapping
    Integer updateAdminAcl(@RequestBody AdminAclParam adminAclParam);
    //删除权限点
    @DeleteMapping("/{id}")
    Integer deleteByAclId(@PathVariable("id") Integer aclId);
    @GetMapping("/{id}")
    AdminAclDto getByAclId(@PathVariable("id") Integer aclId);
}
