package com.yulece.app.management.user.param;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdminDeptParam {

    private Integer deptId;

    @NotBlank(message = "部门名称不能为空")
    @Length(min = 1, max = 20, message = "部门长度请控制在1-20字符之内")
    private String deptName;

    @NotNull(message = "请输入部门所在等级")
    private Integer deptSeq;

    @Length(min = 1, max = 64, message = "部门长度请控制在1-64字符之内")
    private String deptRemark;

    @NotNull(message = "请选在上级部门")
    private Integer deptParentId = 0;

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
}
