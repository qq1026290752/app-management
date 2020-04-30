package com.yulece.app.management.pms.authentication;

import com.yulece.app.management.pms.request.LoginRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  public ResponseEntity login(@RequestBody LoginRequest loginRequest){

   /* String oauthLoginUrl = "http://localhost/auth/authentication/form";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("admin", "123456");

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("code", code);
    params.add("grant_type", "authorization_code");
    params.add("redirect_uri", "http://admin.imooc.com:8080/oauth/callback");

    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

    ResponseEntity<TokenInfo> token = restTemplate.exchange(oauthLoginUrl, HttpMethod.POST, entity, TokenInfo.class);*/
    return null;
  }

}
