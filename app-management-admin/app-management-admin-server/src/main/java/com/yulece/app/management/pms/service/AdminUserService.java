package com.yulece.app.management.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yulece.app.management.pms.dto.user.AdminUserResponse;
import com.yulece.app.management.pms.vo.user.AdminUserCreateRequest;
import com.yulece.app.management.pms.vo.user.AdminUserQueryRequest;
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
    Integer save(AdminUserCreateRequest param, HttpServletRequest request);

    /**
     * 更新接口
     * @param param
     * @param request
     */
    void update(AdminUserUpdateRequest param, HttpServletRequest request);

    /**
     * 激活账号
     * @param active
     */
    void activeAccount(String active);

    /**
     * 获取分页数据
     * @param request
     * @return
     */
    IPage<AdminUserResponse> page(AdminUserQueryRequest request);

    /**
     * 查询一条数据 根据ID查询
     * @param userId
     * @return
     */
    AdminUserResponse findOne(Integer userId);
}
