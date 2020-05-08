package com.yulece.app.management.pms.authentication;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleQueryRequest;
import com.yulece.app.management.utils.RestTemplateTools;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleController
 * @Package com.yulece.app.management.pms.authentication
 * @Description:
 * @Date 2020-05-05 16:40
 **/
@RestController
@RequestMapping("/aclModule")
public class AdminAclModuleController {

  private final RestTemplateTools restTemplateTools;
  public AdminAclModuleController(RestTemplateTools restTemplateTools) {
    this.restTemplateTools = restTemplateTools;
  }

  @PostMapping("/list")
  public ResultVo list(@RequestBody AdminAclModuleQueryRequest aclModuleQueryRequest, HttpServletRequest request){
    String url = "/admin/aclModule/list";
    return restTemplateTools.requestPostObject(aclModuleQueryRequest, url, true, request, HttpMethod.GET, MediaType.APPLICATION_JSON,ResultVo.class);
  }
}
