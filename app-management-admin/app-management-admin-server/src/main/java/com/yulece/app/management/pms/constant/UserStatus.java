package com.yulece.app.management.pms.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 用户状态枚举
 * @author wangyichao@28ph.cn
 * @Title: UserStatus
 * @Package com.yulece.app.management.pms.constant
 * @Description:
 * @Date 2019-11-09 15:54
 **/
@AllArgsConstructor
public enum UserStatus {
    REGISTER(1,"注册"),
    NORMAL(0,"正常"),
    FREEZE(2,"冻结")
    ;
    @Getter
    private Integer code;
    private String value;

}
