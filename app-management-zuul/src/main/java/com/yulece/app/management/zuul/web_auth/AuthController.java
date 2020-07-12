package com.yulece.app.management.zuul.web_auth;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {


    @GetMapping(ZuulAppConstant.LOGIN_JUMP_CONTROLLER)
    public ResultVo login(){
        return ResultVo.createSuccessResult("需要权限认证，请引导用户到登陆界面 ");
    }
}
