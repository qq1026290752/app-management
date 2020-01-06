package com.yulece.app.management.pms.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yulece.app.management.pms.dto.acl.module.AdminAclModuleResponse;
import com.yulece.app.management.pms.entity.AdminAclModule;
import com.yulece.app.management.pms.vo.acl.module.AdminAclModuleQueryRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 接口类
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleRepository
 * @Package com.yulece.app.management.pms.repository
 * @Description:
 * @Date 2019-11-09 16:43
 **/
@Repository
public interface AdminAclModuleRepository extends BaseMapper<AdminAclModule> {
    IPage<AdminAclModuleResponse> findAllByPageAndObject(Page<AdminAclModule> adminAclModulePage,@Param("model") AdminAclModuleQueryRequest param);
}
