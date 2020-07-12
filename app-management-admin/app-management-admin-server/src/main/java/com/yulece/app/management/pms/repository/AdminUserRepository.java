package com.yulece.app.management.pms.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulece.app.management.pms.dto.user.AdminUserResponse;
import com.yulece.app.management.pms.entity.AdminUser;
import com.yulece.app.management.pms.vo.user.AdminUserQueryRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 接口类
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserRepository
 * @Package com.yulece.app.management.pms.repository
 * @Description:
 * @Date 2019-11-09 16:05
 **/
@Repository
public interface AdminUserRepository extends BaseMapper<AdminUser> {
    /**
     * 分页获取数据
     * @param adminUserPage
     * @param request
     * @return
     */
    IPage<AdminUserResponse> findAllByPageAndObject(Page<AdminUser> adminUserPage,
                                                    @Param("model") AdminUserQueryRequest request);
}
