package com.yulece.app.management.pms.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 * 枚举类
 *
 * @author wangyichao@28ph.cn
 * @Title: RoleType
 * @Package com.yulece.app.management.pms.constant
 * @Description:
 * @Date 2019-11-09 16:19
 **/
@AllArgsConstructor
public enum AclType {

    MENU(0,"菜单"),
    BUTTON(1,"按钮"),
    REST(2,"其他")
    ;
    @Getter
    private Integer code;
    private String value;
}
