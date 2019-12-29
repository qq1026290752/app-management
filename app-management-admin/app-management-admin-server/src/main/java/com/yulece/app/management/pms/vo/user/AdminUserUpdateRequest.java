package com.yulece.app.management.pms.vo.user;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: AdminUserUpdateRequest
 * @Package com.yulece.app.management.pms.vo.user
 * @Description:
 * @Date 2019-12-28 23:38
 **/
public class AdminUserUpdateRequest extends AdminUserCreateRequest{

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
