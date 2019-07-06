package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.comments.api.utils.DateUtils;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.user.api.AdminRoleUserService;
import com.yulece.app.management.user.api.AdminUserService;
import com.yulece.app.management.user.dto.AdminUserRoleDto;
import com.yulece.app.management.user.dto.AdminUserVo;
import com.yulece.app.management.user.param.AdminUserParam;
import com.yulece.app.management.user.param.AdminUserRoleParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoleUserService adminRoleUserService;


    @InitBinder
    private void initBinderAdminUserParam(WebDataBinder binder){
        binder.setFieldDefaultPrefix("param");
        binder.registerCustomEditor(Date.class, DateUtils.fromCustomDateEditor(DateUtils.FormDateType.DATE.format));
    }


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
        return ResultVo.createSuccessResult(isSuccess);
    }

    @GetMapping("/user/list")
    public ResultVo<Page> getList(@ModelAttribute("param") AdminUserParam param){
        return ResultVo.createSuccessResult(adminUserService.getList(param));
    }

    /**
     * 给用户增加 角色
     * @return 返回用户存在角色
     */
    @PostMapping("/user/saveRole")
    private ResultVo<AdminUserRoleDto> addUserByRole(@RequestBody AdminUserRoleParam param){
        return ResultVo.createSuccessResult(adminRoleUserService.addUserByRole(param));
    }

    @GetMapping("/user/getUserRole")
    public ResultVo<AdminUserRoleDto> findUserRole(@RequestParam("userId")Integer userId){
        return ResultVo.createSuccessResult(adminRoleUserService.findUserRole(userId));
    }
}
