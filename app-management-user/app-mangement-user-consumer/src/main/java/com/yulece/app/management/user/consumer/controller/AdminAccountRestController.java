package com.yulece.app.management.user.consumer.controller;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.enums.ExceptionEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.user.api.AdminUserService;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

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
    @GetMapping("/hello")
    public ResultVo hello() {
        return ResultVo.createSuccessResult();
    }


    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String toke = StringUtils.substringAfter(header, "bearer ");

        Map<String, Object> jwtClaims =
                Jwts.parser().setSigningKey("yulece".getBytes("UTF-8")).parseClaimsJws(toke).getBody();
        return jwtClaims;

    }
}
