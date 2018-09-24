package com.yulece.app.management.user.provide.pojo;

import java.util.Date;

public class AdminAcl {

    private Integer aclId;

    private String aclName;

    private Integer aclModuleId;

    private String aclUrl;

    private Integer type;

    private Integer status;

    private Integer aclSeq;

    private String aclRemark;

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;

    public Integer getAclId() {
        return aclId;
    }

    public void setAclId(Integer aclId) {
        this.aclId = aclId;
    }

    public String getAclName() {
        return aclName;
    }

    public void setAclName(String aclName) {
        this.aclName = aclName;
    }

    public Integer getAclModuleId() {
        return aclModuleId;
    }

    public void setAclModuleId(Integer aclModuleId) {
        this.aclModuleId = aclModuleId;
    }

    public String getAclUrl() {
        return aclUrl;
    }

    public void setAclUrl(String aclUrl) {
        this.aclUrl = aclUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAclSeq() {
        return aclSeq;
    }

    public void setAclSeq(Integer aclSeq) {
        this.aclSeq = aclSeq;
    }

    public String getAclRemark() {
        return aclRemark;
    }

    public void setAclRemark(String aclRemark) {
        this.aclRemark = aclRemark;
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
}