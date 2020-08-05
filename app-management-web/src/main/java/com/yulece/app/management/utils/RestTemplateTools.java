package com.yulece.app.management.utils;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.enums.ExceptionEnum;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.pms.response.TokenInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: RestTemplateTools
 * @Package com.yulece.app.management.utils
 * @Description:
 * @Date 2020-05-01 17:16
 **/

@Configuration
public class RestTemplateTools{

  private final RestTemplate restTemplate;
  @Value("${zuul.serviceId}")
  private String zuulServiceId;
  @Value("${oauth2.clientId}")
  private String oauthClientId;
  @Value("${oauth2.clientSecret}")
  private String oauthClientSecret;

  public RestTemplateTools(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * 登陆封装
   * @param params
   * @param mediaType
   * @param url
   * @param var4
   * @param <T>
   * @return
   */
  public <T> T postLogin(MultiValueMap<String, String> params, MediaType mediaType, String url, Class<T> var4){
    url = String.format("http://%s%s", zuulServiceId,url);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(mediaType);
    headers.setBasicAuth(oauthClientId, oauthClientSecret);
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
    ResponseEntity<T> t = restTemplate.exchange(url, HttpMethod.POST, entity, var4);
    if(t.getStatusCode().is2xxSuccessful()){
      return t.getBody();
    }
    return null;
  }

  /**
   * get请求数据封装
   * @param params
   * @param originalUrl
   * @param var4
   * @param isToken
   * @param <T>
   * @return
   */
  public <T> T request(MultiValueMap<String, String> params, String originalUrl,
                       boolean isToken, HttpServletRequest request, HttpMethod method, MediaType mediaType, Class<T> var4){
    TokenObtain tokenObtain = new TokenObtain(originalUrl, isToken, request, mediaType).invoke();
    String url = tokenObtain.getUrl();
    TokenInfo userToken = tokenObtain.getUserToken();
    HttpHeaders headers = tokenObtain.getHeaders();
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
    ResponseEntity<T> t = restTemplate.exchange(url, method, entity, var4);
    if(t.getStatusCode().is2xxSuccessful()){
      return t.getBody();
    }
    if(t.getStatusCode().is4xxClientError()){
      assert userToken != null;
      refreshToken(userToken);
      request(params,originalUrl,true,request,method,mediaType,var4);
    }
    return null;
  }

  private void refreshToken(TokenInfo userToken) {
    String refreshTokenUrl = String.format("http://%s%s", zuulServiceId,"/auth/oauth/token");
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(oauthClientId, oauthClientSecret);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type","refresh_token");
    params.add("refresh_token",userToken.getRefresh_token());
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
    ResponseEntity<TokenInfo> responseEntity = restTemplate.exchange(refreshTokenUrl, HttpMethod.GET, entity, TokenInfo.class);
    if(!responseEntity.getStatusCode().is2xxSuccessful()){
      throw new AppException(ExceptionEnum.LOGIN_EXPIRES);
    }
  }

  public <T> T requestPostObject(Object params, String originalUrl,
                       boolean isToken, HttpServletRequest request, HttpMethod method, MediaType mediaType, Class<T> var4){
    TokenObtain tokenObtain = new TokenObtain(originalUrl, isToken, request, mediaType).invoke();
    HttpEntity<Object> entity = new HttpEntity<>(params, tokenObtain.headers);
    ResponseEntity<T> t = restTemplate.exchange(tokenObtain.url, method, entity, var4);
    if(t.getStatusCode().is2xxSuccessful()){
      return t.getBody();
    }
    if(t.getStatusCode().is4xxClientError()){
      assert tokenObtain.userToken != null;
      refreshToken(tokenObtain.userToken);
      requestPostObject(params,originalUrl,true,request,method,mediaType,var4);
    }
    return null;
  }

  private class TokenObtain {
    private final String originalUrl;
    private final boolean isToken;
    private final HttpServletRequest request;
    private final MediaType mediaType;
    private String url;
    private TokenInfo userToken;
    private HttpHeaders headers;

    public TokenObtain(String originalUrl, boolean isToken, HttpServletRequest request, MediaType mediaType) {
      this.originalUrl = originalUrl;
      this.isToken = isToken;
      this.request = request;
      this.mediaType = mediaType;
    }

    public String getUrl() {
      return url;
    }

    public TokenInfo getUserToken() {
      return userToken;
    }

    public HttpHeaders getHeaders() {
      return headers;
    }

    public TokenObtain invoke() {
      url = String.format("http://%s%s", zuulServiceId, originalUrl);
      userToken = null;
      headers = new HttpHeaders();
      if(isToken){
        userToken = (TokenInfo)request.getSession(true).getAttribute("userToken");
        if(userToken!=null){
          headers.setContentType(mediaType);
          String token = userToken.getToken_type().concat(" ").concat(userToken.getAccess_token());
          headers.set("Authorization",token);
        }
      }
      return this;
    }
  }
}
