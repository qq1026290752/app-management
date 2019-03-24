package com.yulece.app.management.commons.utils.enums;

public enum AppParamEnum {

    USER_NOT_EXIST_ERROR(0,"查询用户不存在"),
    USER_USERNAME_EXIST_ERROR(1,"用户名已存在"),
    USER_EMAIL_EXIST(2,"邮箱已存在"),
    USER_PHONE_EXIST(4,"联系方式已存在"),
    PARAMS_NOT_LEGAL(5,"所带入参数不合法"),
    USER_ACTIVE_KEY_ERROR(6,"用户激活KEY不正确,或者KEY超过有效时间"),
    DEPT_NAME_EXIST_ERROR(7,"部门名称已经存在"),
    DEPT_SEQ_EXIST_ERROR(8,"部门所在排序存在"),
    DEPT_EXIST_DEPT(9,"该部门下存在子部门"),
    DEPT_EXIST_USER(10,"该部门下存在用户"),
    ACL_MODULE_NAME_EXIST(11,"权限模块名称已经存在"),
    ACL_MODULE_SEQ_EXIST(12,"权限模块下排序存在"),
    ACL_MODULE_SEQ_EXIST_SUBMODULE(13,"该权限模块下存在子模块"),
    ACL_MODULE_SEQ_EXIST_ACL(14,"该权限模块下存在权限点"),
    ACL_MODULE_NOT_EXIST(18,"权限模块不存在 "),
    ACL_ACL_NAME_EXIST_ERROR(15,"权限点名称已经存在"),
    ACL_ACL_SEQ_EXIST_ERROR(16,"权限点下排序存在"),
    ACL_ACL_NULL_EXIST(17,"权限点不存在,请刷新"),
    ROLE_NAME_EXIST(18,"角色名称已存在"),
    ROLE_SEQ_EXIST(19,"角色排序已存在")
    ;
    private int code;
    private String message;

    AppParamEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
