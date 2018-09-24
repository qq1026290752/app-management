package com.yulece.app.management.user.provide.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class AdminRoleAcl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleAclId;

    private Integer roleId;

    private Integer aclId;

    private String operator;

    private String operateIp;

    private Date createTime;

    private Date updateTime;

    public Integer getRoleAclId() {
        return roleAclId;
    }

    public void setRoleAclId(Integer roleAclId) {
        this.roleAclId = roleAclId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAclId() {
        return aclId;
    }

    public void setAclId(Integer aclId) {
        this.aclId = aclId;
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