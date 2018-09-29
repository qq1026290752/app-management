package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.enums.ExceptionEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AdminAccountRestController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/active")
    public ResultVo active(String key) {
        boolean active = adminUserService.active(key);
        if(active){
            return ResultVo.createSuccessResult();
        }else {
            throw new AppException(ExceptionEnum.ACTIVE_FAILURE);
        }
    }
}
