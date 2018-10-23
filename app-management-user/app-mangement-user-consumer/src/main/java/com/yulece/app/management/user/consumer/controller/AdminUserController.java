package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.entity.AdminUserParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);

    @GetMapping("/user/{id}")
    public ResultVo<AdminUserVo> getById(HttpServletRequest request, @PathVariable("id") Integer id) {
        AdminUserVo adminUserVo = adminUserService.getById(id);
        return ResultVo.createSuccessResult(adminUserVo);
    }

    @PutMapping("/user/update")
    public ResultVo update(@RequestBody AdminUserParam param) {
        boolean isSuccess = adminUserService.update(param);
        if(isSuccess){
            return ResultVo.createSuccessResult();
        }
        return ResultVo.createErrorResult("服务器内部错误,请稍后重试",505);
    }
    @PostMapping("/user/save")
    public ResultVo create(@RequestBody AdminUserParam param) {
        boolean isSuccess = adminUserService.create(param);


        if (isSuccess) {
            return ResultVo.createSuccessResult();
        } else {
            return ResultVo.createErrorResult("保存失败,稍后重试");
        }
    }
}
