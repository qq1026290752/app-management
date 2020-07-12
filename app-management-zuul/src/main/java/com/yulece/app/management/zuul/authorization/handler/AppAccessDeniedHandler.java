package com.yulece.app.management.zuul.authorization.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yulece.app.management.commons.utils.ResultVo;
import com.yulece.app.management.zuul.constant.ZuulAppConstant;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("AppAccessDeniedHandler")
public class AppAccessDeniedHandler implements AccessDeniedHandler{

	@Autowired
	private ObjectMapper objectMapper;
	
		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response,
				AccessDeniedException accessDeniedException) throws IOException, ServletException {
			response.setContentType(ZuulAppConstant.CONTENT_TYPE_JSON);
			response.setStatus(HttpStatus.SC_FORBIDDEN);
			response.getWriter().write(objectMapper.writeValueAsString(ResultVo.createErrorResult("当前用户访问权限不够,请联系管理员增加对应权限",403)));
		}

}
