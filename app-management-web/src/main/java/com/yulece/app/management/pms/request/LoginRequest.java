package com.yulece.app.management.pms.request;

import lombok.Data;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: LoginRequest
 * @Package com.yulece.app.management.pms.request
 * @Description:
 * @Date 2020-04-28 21:22
 **/
@Data
public class LoginRequest {

  private String userName;
  private String passWord;
  private Boolean isCheck;
}
