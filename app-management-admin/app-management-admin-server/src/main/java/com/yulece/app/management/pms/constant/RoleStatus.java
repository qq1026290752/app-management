package com.yulece.app.management.pms.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 角色状态枚举类
 *
 * @author wangyichao@28ph.cn
 * @Title: RoleStatus
 * @Package com.yulece.app.management.pms.constant
 * @Description:
 * @Date 2019-11-09 16:23
 **/
@AllArgsConstructor
public enum RoleStatus {

    NORMAL(0,"正常"),
    ABANDON(1,"弃用")
    ;
    @Getter
    private Integer code;
    private String value;
}
