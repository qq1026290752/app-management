package com.yulece.app.management.pms.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yulece.app.management.pms.entity.AdminUser;
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
}
