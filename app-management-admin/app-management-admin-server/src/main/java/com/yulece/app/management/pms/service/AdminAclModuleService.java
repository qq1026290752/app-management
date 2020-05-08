package com.yulece.app.management.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yulece.app.management.pms.dto.acl.module.AdminAclModuleResponse;
import com.yulece.app.management.pms.entity.tree.AdminAclModuleTreeDto;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleCreateRequest;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleQueryRequest;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleUpdateRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleService
 * @Package com.yulece.app.management.pms.service
 * @Description:
 * @Date 2020-01-05 18:48
 **/
public interface AdminAclModuleService {
    /**
     * 保存添加模块
      * @param request
     * @param param
     * @return
     */
    Integer save(HttpServletRequest request, AdminAclModuleCreateRequest param);

    /**
     * 更新权限模块
     * @param request
     * @param param
     * @return
     */
    Integer update(HttpServletRequest request, AdminAclModuleUpdateRequest param);

    /**
     * 查询分页权限模块
     * @param param
     * @return
     */
    IPage<AdminAclModuleResponse> page(AdminAclModuleQueryRequest param);

    /**
     * 根据ID查询权限模块
     * @param id
     * @return
     */
    AdminAclModuleResponse findOne(Integer id);

    /**
     * 查询权限模块树
     * @return
     */
    List<AdminAclModuleTreeDto> trees();
}
