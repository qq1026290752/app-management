package com.yulece.app.management.pms.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAccountController
 * @Package com.yulece.app.management.pms.controller
 * @Description:
 * @Date 2020-01-05 11:28
 **/
@RestController
@RequestMapping("account")
public class AdminAccountController {

    private final AdminUserService adminUserService;
    @Autowired
    public AdminAccountController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    @GetMapping("/{active}/active")
    public ResultVo activeAccount(@PathVariable("active") String active){
        adminUserService.activeAccount(active);
        return ResultVo.createSuccessResult();
    }
}
