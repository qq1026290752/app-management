package com.yulece.app.management.user.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminAclModuleDto
 * @Package com.yulece.app.management.user.dto
 * @Description:
 * @Date 2019-01-10 21:37
 **/
@Data
public class AdminAclModuleDto {

    private Integer moduleId;

    private String moduleName;

    private Integer moduleParentId;

    private String moduleLevel;

    private Integer status;

    private Integer moduleSeq;

    private String moduleRemark;

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;

    private List<AdminAclModuleDto> adminAclModelDtos = Lists.newArrayList();

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getModuleParentId() {
        return moduleParentId;
    }

    public void setModuleParentId(Integer moduleParentId) {
        this.moduleParentId = moduleParentId;
    }

    public String getModuleLevel() {
        return moduleLevel;
    }

    public void setModuleLevel(String moduleLevel) {
        this.moduleLevel = moduleLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getModuleSeq() {
        return moduleSeq;
    }

    public void setModuleSeq(Integer moduleSeq) {
        this.moduleSeq = moduleSeq;
    }

    public String getModuleRemark() {
        return moduleRemark;
    }

    public void setModuleRemark(String moduleRemark) {
        this.moduleRemark = moduleRemark;
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

    public List<AdminAclModuleDto> getAdminAclModelDtos() {
        return adminAclModelDtos;
    }

    public void setAdminAclModelDtos(List<AdminAclModuleDto> adminAclModelDtos) {
        this.adminAclModelDtos = adminAclModelDtos;
    }
}
