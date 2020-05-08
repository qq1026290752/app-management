package com.yulece.app.management.pms.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.dto.acl.module.AdminAclModuleResponse;
import com.yulece.app.management.pms.entity.tree.AdminAclModuleTreeDto;
import com.yulece.app.management.pms.service.AdminAclModuleService;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleCreateRequest;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleQueryRequest;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleUpdateRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleController
 * @Package com.yulece.app.management.pms.controller
 * @Description:
 * @Date 2020-01-05 18:44
 **/
@RestController
@RequestMapping("/aclModule")
public class AdminAclModuleController {

    private final AdminAclModuleService adminAclModuleService;

    public AdminAclModuleController(AdminAclModuleService adminAclModuleService) {
        this.adminAclModuleService = adminAclModuleService;
    }

    @PostMapping
    public ResultVo<Void> create(HttpServletRequest request, @RequestBody AdminAclModuleCreateRequest params){
        adminAclModuleService.save(request, params);
        return ResultVo.createSuccessResult();
    }

    @PutMapping
    public ResultVo<Void> update(HttpServletRequest request, @RequestBody AdminAclModuleUpdateRequest params){
        adminAclModuleService.update(request, params);
        return ResultVo.createSuccessResult();
    }

    @PostMapping("/list")
    public ResultVo<IPage<AdminAclModuleResponse>> list(@RequestBody AdminAclModuleQueryRequest request){
        IPage<AdminAclModuleResponse> list = adminAclModuleService.page(request);
        return ResultVo.createSuccessResult(list);
    }

    @GetMapping("/trees")
    public ResultVo<List<AdminAclModuleTreeDto>> tree(){
        List<AdminAclModuleTreeDto> trees = adminAclModuleService.trees();
        return ResultVo.createSuccessResult(trees);
    }

    @GetMapping("/{moduleId}")
    public ResultVo<AdminAclModuleResponse> findOne(@PathVariable Integer moduleId){
        AdminAclModuleResponse aclModuleResponse = adminAclModuleService.findOne(moduleId);
        return ResultVo.createSuccessResult(aclModuleResponse);
    }
}
