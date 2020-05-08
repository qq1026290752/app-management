package com.yulece.app.management.pms.dto.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserListResponse
 * @Package com.yulece.app.management.pms.dto.user
 * @Description:
 * @Date 2019-12-28 23:46
 **/
@Data
public class AdminUserResponse {

    private Integer userId;
    private String userName;
    private String nikeName;
    private String phone;
    private String mail;
    private String headerUrl;
    private Integer status;
    private String userRemark;
    private Integer userDept;
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date createTime;
    private String deptName;
}
