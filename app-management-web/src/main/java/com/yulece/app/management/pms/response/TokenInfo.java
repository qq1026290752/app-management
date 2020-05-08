package com.yulece.app.management.pms.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: TokenInfo
 * @Package com.yulece.app.management.pms.response
 * @Description:
 * @Date 2020-04-30 22:51
 **/
@Data
public class TokenInfo implements Serializable {

  private static final long serialVersionUID = 4092518237388655704L;
  private String access_token;
  private String token_type;
  private String refresh_token;
  private Integer expires_in;
  private String scope;
  private String author;
  private String jti;
}
