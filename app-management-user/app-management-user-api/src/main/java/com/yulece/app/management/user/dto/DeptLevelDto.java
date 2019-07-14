package com.yulece.app.management.user.dto;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:DeptLevelDto
 * @Package com.yulece.app.management.user.dto
 * @Description:
 * @Date 创建时间 2018/10/23-22:39
 **/
public class DeptLevelDto implements Serializable {

    private static final long serialVersionUID = 4935936718852192274L;

    private Integer deptId;

    private String deptName;

    private String deptLevel;

    private Integer deptSeq;

    private String deptRemark;

    private Integer deptParentId;

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;

    private List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();


    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public Integer getDeptSeq() {
        return deptSeq;
    }

    public void setDeptSeq(Integer deptSeq) {
        this.deptSeq = deptSeq;
    }

    public String getDeptRemark() {
        return deptRemark;
    }

    public void setDeptRemark(String deptRemark) {
        this.deptRemark = deptRemark;
    }

    public Integer getDeptParentId() {
        return deptParentId;
    }

    public void setDeptParentId(Integer deptParentId) {
        this.deptParentId = deptParentId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<DeptLevelDto> getDeptLevelDtoList() {
        return deptLevelDtoList;
    }

    public void setDeptLevelDtoList(List<DeptLevelDto> deptLevelDtoList) {
        this.deptLevelDtoList = deptLevelDtoList;
    }
}
