package com.yulece.app.management.user.provide.repositories;

import com.yulece.app.management.comments.api.entity.Page;
import com.yulece.app.management.user.dto.AdminAclModuleDto;
import com.yulece.app.management.user.param.AdminAclModuleParam;
import com.yulece.app.management.user.provide.pojo.AdminAclModule;
import com.yulece.app.management.user.provide.utils.AppUserProviderMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleRepository
 * @Package com.yulece.app.management.user.provide.repositories
 * @Description:
 * @Date 2019-01-10 21:57
 **/
public interface AdminAclModuleRepository extends AppUserProviderMapper<AdminAclModule> {

    /**
     * 查询存在该模块名称的内容条数
     * @param moduleId
     * @param moduleParentId
     * @param moduleName
     * @return
     */
    Integer countAclModuleName(@Param("moduleId") Integer moduleId, @Param("moduleParentId")
            Integer moduleParentId,@Param("moduleName") String moduleName);

    Integer countAclModuleSeq(@Param("moduleId") Integer moduleId, @Param("moduleParentId")
            Integer moduleParentId, @Param("moduleSeq") Integer moduleSeq);

    /**
     * 查询该模块下是否存在子模块
     * @param aclModelId
     * @return
     */
    Integer existAclModuleCount(@Param("moduleId")Integer aclModelId);

    List<AdminAclModule> findChildModule( @Param("level") String moduleLevel);

    List<AdminAclModuleDto> getAclModuleList(@Param("param") AdminAclModuleParam param, Page page);
}
