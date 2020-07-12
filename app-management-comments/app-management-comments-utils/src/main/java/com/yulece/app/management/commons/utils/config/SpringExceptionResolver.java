package com.yulece.app.management.commons.utils.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.commons.utils.exception.AppException;
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
import java.util.Map;

/**
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @program: app-management
 * @title:SpringExceptionResolver
 * @Package com.yulece.app.management.comments.api.exception
 * @Description:
 * @Date 创建时间 2018/10/22-10:38
 **/
@ControllerAdvice
public class SpringExceptionResolver {



    private final ObjectMapper objectMapper;
    @Autowired
    public SpringExceptionResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleGlobalException(HttpServletRequest request, Exception e, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        if (e instanceof NoHandlerFoundException) {
            response.getWriter().print(objectMapper.writeValueAsString(ResultVo.createErrorResult(e.getMessage(), 404)));
        } else if (e instanceof AppException) {
            response.getWriter().print(objectMapper.writeValueAsString(ResultVo.createErrorResult(e.getMessage(), 500)));
        } else {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("code") && errorMessage.contains("message")) {
                String message = errorMessage.substring(errorMessage.indexOf("{"));
                ResultVo parse = objectMapper.readValue(message,ResultVo.class);
                response.getWriter().print(objectMapper.writeValueAsString(parse));
            } else {
                response.getWriter().print(objectMapper.writeValueAsString(ResultVo.createErrorResult("程序内部错误,请稍后重试", 500)));
            }

        }
    }
}
