package com.yulece.app.management.zuul.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.exception.AppException;
import com.yulece.app.management.commons.utils.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: SpringExceptionResolver
 * @Package com.yulece.app.greenroom.configuration
 * @Description: 系统全局异常处理类
 * @Date 创建时间2018/8/11-20:30
 **/
@ControllerAdvice
public class SpringExceptionResolver {

    @Autowired
    private ObjectMapper objectMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleGlobalException(HttpServletRequest request, Exception e, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        if (e instanceof NoHandlerFoundException) {
            response.getWriter().print(objectMapper.writeValueAsString(ResultVo.createErrorResult(e.getMessage(), 404)));
        } else if (e instanceof AppException || e instanceof ParamException) {
            response.getWriter().print(objectMapper.writeValueAsString(ResultVo.createErrorResult(e.getMessage(), 500)));
        } else {
            response.getWriter().print(objectMapper.writeValueAsString(ResultVo.createErrorResult("服务器内部异常请联系管理员", 500)));
        }
    }
}
