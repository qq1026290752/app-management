package com.yulece.app.management.commons.utils.exception;

import com.yulece.app.management.commons.utils.enums.AppParamEnum;
import com.yulece.app.management.commons.utils.enums.SecurityEnum;
import org.springframework.security.core.AuthenticationException;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: SMSException
 * @Package com.yulece.app.management.commons.utils.exception
 * @Description:
 * @Date 2019-02-24 19:08
 **/
public class SMSException extends AuthenticationException {

    private Integer code;

    public SMSException(SecurityEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
    public SMSException(AppParamEnum paramEnum) {
        super(paramEnum.getMessage());
        this.code = paramEnum.getCode();
    }
}
