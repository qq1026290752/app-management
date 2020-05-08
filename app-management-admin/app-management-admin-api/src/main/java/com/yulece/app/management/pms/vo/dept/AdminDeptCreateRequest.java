package com.yulece.app.management.pms.vo.dept;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminDeptCreateRequest
 * @Package com.yulece.app.management.pms.vo
 * @Description:
 * @Date 2019-11-10 14:57
 **/
@Data
public class AdminDeptCreateRequest {

    @NotBlank(message = "部门名称不能为空")
    @Length(min = 1,max = 32,message = "部门名称长度需要在1-32个字符之间0")
    private String deptName;
    @NotNull(message = "部门排序不能为空")
    private Integer deptSeq;
    @Length(min = 1,max = 500,message = "部门备注需要在1-500个字符之间")
    private String deptRemark;
    @NotNull(message = "部门上级ID不能为空")
    private Integer deptParentId = 0;
    @NotNull(message = "部门状态不能为空")
    private Integer status;

    public static  final String LEVEL = "0" ;

    public String getLEVEL(String parentLeVel) {
        if (StringUtils.isEmpty(parentLeVel)){
            return LEVEL;
        }else {
            return  parentLeVel +"," + deptParentId;
        }
    }

    public static String getLEVEL(String parentLeVel,Integer deptParentId) {
        if (StringUtils.isEmpty(parentLeVel)){
            return LEVEL;
        }else {
            return  parentLeVel +"," + deptParentId;
        }
    }
}
