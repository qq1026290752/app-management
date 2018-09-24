package com.yulece.app.management.commons.enums;

public enum ParamEnum {
		USER_NOT_EXIST_ERROR(0,"查询用户不存在"),
		USER_USERNAME_EXIST_ERROR(1,"用户名已存在"),
		USER_EMAI_EXIST_ERROE(2,"邮箱已存在"),
		USER_TLEL_PHONE_EXIST_RROE(4,"联系方式已存在"),
		PARAMS_NOT_LEGAL(5,"所带入参数不合法"),
		USER_ACTIVE_KEY_ERROR(6,"用户激活KEY不正确,或者KEY超过有效时间"),
		DEPT_DEPTNAME_EXIST_ERRPR(7,"部门名称已经存在"),
		DEPT_DEPTSEQL_EXIST_ERRPR(8,"部门所在排序存在"),
		DEPT_EXIST_DEPT(9,"该部门下存在子部门"),
		DEPT_EXIST_USER(10,"该部门下存在用户"),
		ACL_MODEL_ACLMODEL_NAME_EXIST_ERRPR(11,"权限模块名称已经存在"),
		ACL_MODEL_ACLMODEL_SEQ_EXIST_ERRPR(12,"权限模块下排序存在"),
		ACL_MODEL_ACLMODEL_EXIST_ACLMODEL(13,"该权限模块下存在子模块"),
		ACL_MODEL_ACLMODEL_EXIST_ACL(14,"该权限模块下存在权限点"),
		ACL_ACL_NAME_EXIST_ERRPR(15,"权限点名称已经存在"),
		ACL_ACL_SEQ_EXIST_ERRPR(16,"权限点下排序存在"),
		;
	    private int code;
	    private String message;

	    ParamEnum(Integer code, String message){
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
