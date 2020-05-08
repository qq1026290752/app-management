package com.yulece.app.management.pms.entity.tree;

import com.yulece.app.management.pms.entity.AdminDept;

import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptTreeDto
 * @Package com.yulece.app.management.pms.dto
 * @Description:
 * @Date 2019-12-28 19:50
 **/
public class AdminDeptTreeDto extends AdminDept {

    private List<AdminDeptTreeDto> trees;

    public List<AdminDeptTreeDto> getTrees() {
        return trees;
    }

    public void setTrees(List<AdminDeptTreeDto> trees) {
        this.trees = trees;
    }
}
