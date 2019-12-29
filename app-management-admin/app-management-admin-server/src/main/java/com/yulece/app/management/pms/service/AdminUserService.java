package com.yulece.app.management.pms.service;

import com.yulece.app.management.pms.vo.user.AdminUserCreateRequest;
import com.yulece.app.management.pms.vo.user.AdminUserUpdateRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserService
 * @Package com.yulece.app.management.pms.service
 * @Description:
 * @Date 2019-12-28 22:08
 **/
public interface AdminUserService {
    /**
     * 保存用户接口
     * @param param
     * @param request
     */
    String save(AdminUserCreateRequest param, HttpServletRequest request);

    /**
     * 更新接口
     * @param param
     * @param request
     */
    void update(AdminUserUpdateRequest param, HttpServletRequest request);



}
