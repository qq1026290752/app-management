package com.yulece.app.management.user.api;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.comments.api.interceptor.FeignHeaderInterceptor;
import com.yulece.app.management.user.dto.AdminAclModuleDto;
import com.yulece.app.management.user.param.AdminAclModuleParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "app-management-user-provider",configuration = FeignHeaderInterceptor.class)
public interface AdminAclModuleService {

    /**
     * @desc 添加权限模块
     * @param param 权限模块参数
     * @return 权限模块ID
     */
    @PostMapping("/aclModule")
    Integer save(AdminAclModuleParam param);


    /**
     * 修改权限模块
     * @param param
     * @return
     */
    @PutMapping("/aclModule")
    Integer update(AdminAclModuleParam param);

    /**
     * 删除权限模块
     * @param aclModelId
     * @return
     */
    @DeleteMapping("/aclModule/{aclModelId}")
    Integer delete(@PathVariable("aclModelId")Integer aclModelId);

    /**
     * 根据权限模块ID查询权限模块
     * @param aclModelId
     * @return
     */
    @GetMapping("/aclModule/{aclModelId}")
    AdminAclModuleDto getAdminAclModelById(@PathVariable("aclModelId")Integer aclModelId);

    @PostMapping("/aclModule/list")
    Page<AdminAclModuleDto> getList(@RequestBody AdminAclModuleParam param);
}
