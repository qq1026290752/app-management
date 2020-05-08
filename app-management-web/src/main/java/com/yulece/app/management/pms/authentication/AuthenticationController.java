package com.yulece.app.management.pms.authentication;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.request.LoginRequest;
import com.yulece.app.management.pms.response.TokenInfo;
import com.yulece.app.management.utils.RestTemplateTools;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AuthenticationController
 * @Package com.yulece.app.management.pms.authentication
 * @Description:
 * @Date 2020-04-25 20:28
 **/
@RestController
@RequestMapping("pms")
public class AuthenticationController {

  private final RestTemplateTools restTemplateTools;
  public AuthenticationController(RestTemplateTools restTemplateTools) {
    this.restTemplateTools = restTemplateTools;
  }


  @RequestMapping(value = "login",method = RequestMethod.POST)
  public ResultVo login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
    String url="/auth/authentication/form";
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("username", loginRequest.getUserName());
    params.add("password", loginRequest.getPassWord());
    TokenInfo tokenInfo = restTemplateTools.postLogin(params, MediaType.APPLICATION_FORM_URLENCODED, url, TokenInfo.class);
    request.getSession(true).setAttribute("userToken",tokenInfo);
    return ResultVo.createSuccessResult();
  }

  @RequestMapping(value = "getMe",method = RequestMethod.GET)
  public ResultVo<String> getMe(HttpServletRequest request){
    String token = restTemplateTools.request(null, "/auth/me", true, request,HttpMethod.GET,MediaType.APPLICATION_JSON,String.class);
    return ResultVo.createSuccessResult(token);
  }
}
