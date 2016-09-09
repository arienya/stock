package com.bimt.thesisquery.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.bimt.thesisquery.common.ResultJson;

/**
 * 系统异常通用处理
 * 
 * @author xufeng
 *
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {		
		logger.error(ex.getMessage());
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			return null;
		}
		
		ResultJson json = ResultJson.error(ex.getMessage());
		
		writer.write(JSON.toJSONString(json));
		writer.flush();
		return null;
	}
}
