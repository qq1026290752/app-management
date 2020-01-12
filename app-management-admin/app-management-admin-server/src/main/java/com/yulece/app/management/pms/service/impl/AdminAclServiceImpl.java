package com.yulece.app.management.pms.service.impl;

import com.yulece.app.management.pms.repository.AdminAclRepository;
import com.yulece.app.management.pms.service.AdminAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclServiceImpl
 * @Package com.yulece.app.management.pms.service.impl
 * @Description:
 * @Date 2020-01-06 21:59
 **/
@Service
public class AdminAclServiceImpl implements AdminAclService {

    @Autowired
    private final AdminAclRepository adminAclRepository;

    public AdminAclServiceImpl(AdminAclRepository adminAclRepository) {
        this.adminAclRepository = adminAclRepository;
    }
}
