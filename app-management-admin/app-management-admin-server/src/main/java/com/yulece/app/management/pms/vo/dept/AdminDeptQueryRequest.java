package com.yulece.app.management.pms.vo.dept;

import com.yulece.app.management.commons.utils.page.PageParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserQueryRequest
 * @Package com.yulece.app.management.pms.vo
 * @Description:
 * @Date 2019-11-16 11:41
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDeptQueryRequest extends PageParams {

    private String deptName;
    private Integer parentId;
    private Integer status;

}
