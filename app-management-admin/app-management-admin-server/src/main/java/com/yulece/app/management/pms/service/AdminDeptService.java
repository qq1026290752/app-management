package com.yulece.app.management.pms.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yulece.app.management.pms.dto.dept.AdminDeptListResponse;
import com.yulece.app.management.pms.dto.dept.AdminDeptTreeDto;
import com.yulece.app.management.pms.vo.dept.AdminDeptCreateRequest;
import com.yulece.app.management.pms.vo.dept.AdminDeptQueryRequest;
import com.yulece.app.management.pms.vo.dept.AdminDeptUpdateRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 接口类
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptService
 * @Package com.yulece.app.management.pms.service
 * @Description:
 * @Date 2019-11-09 19:18
 **/
public interface AdminDeptService {
        /**
         *创建PMS部门
         * @param httpServletRequest
         * @param param
         * @return
         */
        Integer create(HttpServletRequest httpServletRequest, AdminDeptCreateRequest param);

        /**
         * 更新部门
         * @param httpServletRequest
         * @param param
         * @return
         */
        Integer update(HttpServletRequest httpServletRequest, AdminDeptUpdateRequest param);

        /**
         * 分页查询部门信息
         * @param request
         * @return
         */
        IPage<AdminDeptListResponse> list(AdminDeptQueryRequest request);

        /**
         * 查询部门等级树
         * @return
         */
        List<AdminDeptTreeDto> deptTree();

        /**
         * 查询单一数据
         * @param deptId
         * @return
         */
        AdminDeptListResponse findOne(Integer deptId);
}
