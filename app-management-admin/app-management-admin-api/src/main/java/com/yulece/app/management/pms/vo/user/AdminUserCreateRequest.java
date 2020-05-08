package com.yulece.app.management.pms.vo.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserCreateRequest
 * @Package com.yulece.app.management.pms.vo.dept.user
 * @Description:
 * @Date 2019-12-28 22:11
 **/
@Data
public class AdminUserCreateRequest {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6,max = 30,message = "用户名请在6-30字符之间")
    private String userName;
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6,max = 30,message = "用户密码请在6-30字符之间")
    private String passWord;
    @Length(min = 6,max = 30,message = "用户昵称请在6-30字符之间")
    private String nikeName;
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11,max = 11,message = "手机号为11位")
    private String phone;
    @NotBlank(message = "用户邮箱不能为空")
    @Length(min = 10,max = 45,message = "用户邮箱请控制在10-45个字符之间")
    private String mail;
    @Length(min = 1,max = 225,message = "请上传用户头像")
    private String headerUrl;
    @NotNull(message = "请选择当前用户的状态")
    private Integer status = 0;
    @Length(min = 1,max = 240,message = "用户备注请在1-240个字符之间")
    private String userRemark;
    @NotNull(message = "请选择当前用户所属部门")
    private Integer userDept = 1;
}
