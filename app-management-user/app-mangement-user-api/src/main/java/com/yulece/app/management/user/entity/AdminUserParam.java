package com.yulece.app.management.user.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户注册信息服务
 */
@Data
public class AdminUserParam {


    private Integer userId;

    @NotBlank(message = "用户登录名称不能为空")
    @Length(min = 6, max = 30, message = "用户登录名在6-30个字符之间")
    private String userName;

    @Length(min = 1, max = 30, message = "用户昵称在1-30个字符之间")
    private String nikeName;

    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "请输入正确的手机号")
    private String telephone;

    @NotBlank(message = "请您输入此用户常用邮箱")
    @Email(message = "请输入正确的邮箱地址")
    private String mail;


    @Length(min = 1, max = 64, message = "用户备注长度应在6-40为之间")
    private String userRemark;

    @NotNull(message = "请选择用户所属部门")
    private Integer userDept;
    @NotNull(message = "请上传您喜欢的头像")
    private String headerUrl;

}
