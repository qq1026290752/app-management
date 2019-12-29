package com.yulece.app.management.pms.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.service.AdminUserService;
import com.yulece.app.management.pms.vo.user.AdminUserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
