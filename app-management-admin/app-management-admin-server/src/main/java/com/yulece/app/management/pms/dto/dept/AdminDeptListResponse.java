package com.yulece.app.management.pms.dto.dept;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptListResponse
 * @Package com.yulece.app.management.pms.dto
 * @Description:
 * @Date 2019-12-22 21:15
 **/
@Data
public class AdminDeptListResponse {

    private Integer deptId;
    private String deptName;
    private Integer status;
    private String parentDeptName;
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date createTime;
    private Integer parentDeptId;
    private Integer deptSort;
}
