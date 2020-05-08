package com.yulece.app.management.pms.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yulece.app.management.pms.entity.tree.AdminDeptTreeDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDept
 * @Package com.yulece.app.management.pms.entity
 * @Description:
 * @Date 2019-11-09 16:45
 **/
@Data
public class AdminDept {

    @TableId(type = IdType.AUTO)
    private Integer deptId;
    private String deptName;
    private String deptLevel;
    private Integer deptSeq;
    private String deptRemark;
    @TableField(fill = FieldFill.INSERT)
    private String operator;
    @TableLogic(value = "0",delval = "1")
    private Integer status;
    private String operateIp;
    private Integer deptParentId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private Date updateTime;

    public AdminDeptTreeDto adapt(AdminDept dept) {
        AdminDeptTreeDto dto = new AdminDeptTreeDto();
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }
}
