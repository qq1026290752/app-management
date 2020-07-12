package com.yulece.app.management.pms.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulece.app.management.pms.dto.dept.AdminDeptListResponse;
import com.yulece.app.management.pms.entity.AdminDept;
import com.yulece.app.management.pms.vo.dept.AdminDeptQueryRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptRepository
 * @Package com.yulece.app.management.pms.repository
 * @Description:
 * @Date 2019-11-09 16:52
 **/
@Repository
public  interface AdminDeptRepository extends BaseMapper<AdminDept> {
    /**
     * 根据条件查询
     * @param page
     * @param request
     * @return
     */
    IPage<AdminDeptListResponse> findAllByPageAndObject(Page<AdminDept> page, @Param("page") AdminDeptQueryRequest request);
}
