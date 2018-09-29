package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/user/{id}")
    public ResultVo<AdminUserVo> getById(@PathVariable("id") Integer id) {
        AdminUserVo adminUserVo = adminUserService.getById(id);
        return ResultVo.createSuccessResult(adminUserVo);
    }

    @PutMapping("/user/update")
    public ResultVo update(@RequestBody AdminUserParam param) {
        adminUserService.create(param);
        return ResultVo.createSuccessResult();
    }
    @PostMapping("/user/save")
    public ResultVo create(@RequestBody AdminUserParam param) {
        boolean isSuccess = adminUserService.create(param);
        if (isSuccess){
            return ResultVo.createSuccessResult();
        } else {
            return ResultVo.createErrorResult("保存失败,稍后重试");
        }
    }
}
