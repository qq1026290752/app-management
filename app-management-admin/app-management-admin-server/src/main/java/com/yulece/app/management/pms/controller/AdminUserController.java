package com.yulece.app.management.pms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.dto.user.AdminUserResponse;
import com.yulece.app.management.pms.service.AdminUserService;
import com.yulece.app.management.pms.vo.user.AdminUserCreateRequest;
import com.yulece.app.management.pms.vo.user.AdminUserQueryRequest;
import com.yulece.app.management.pms.vo.user.AdminUserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserController
 * @Package com.yulece.app.management.pms.controller
 * @Description:
 * @Date 2019-12-28 22:05
 **/
@RestController
@RequestMapping("/user")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }
    @PostMapping
    public ResultVo save(@RequestBody AdminUserCreateRequest param, HttpServletRequest request){
        adminUserService.save(param,request);
        return ResultVo.createSuccessResult();
    }

    @PutMapping
    public ResultVo update(@RequestBody AdminUserUpdateRequest param, HttpServletRequest request){
        adminUserService.update(param,request);
        return ResultVo.createSuccessResult();
    }
    @PostMapping("/list")
    public ResultVo<IPage<AdminUserResponse>> update(@RequestBody AdminUserQueryRequest model, HttpServletRequest request){
        IPage<AdminUserResponse> page = adminUserService.page(model);
        return ResultVo.createSuccessResult(page);
    }

    @GetMapping("{userId}")
    public ResultVo<AdminUserResponse> findOne(@PathVariable Integer userId){
        AdminUserResponse ones = adminUserService.findOne(userId);
        return ResultVo.createSuccessResult(ones);
    }

}
