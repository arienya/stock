package com.bimt.thesisquery.common.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bimt.thesisquery.common.persistence.BaseModel;
import com.bimt.thesisquery.common.util.Const;
import com.bimt.thesisquery.common.util.DateUtils;
import com.bimt.thesisquery.common.util.HttpUtils;
import com.bimt.thesisquery.common.util.StringUtils;
import com.bimt.thesisquery.common.ResultJson;
import com.bimt.thesisquery.common.beanvalidator.ValidationResult;
import com.bimt.thesisquery.common.beanvalidator.ValidationUtils;

public class BaseController {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @return 验证成功：hasErrors返回true；严重失败：将错误信息添加到 errorMsg 中
	 */
	protected ValidationResult beanValidator(Object object) {
		ValidationResult result = new ValidationResult();
		try{
			result = ValidationUtils.validateModel(object);			
		}catch(ConstraintViolationException ex){
			logger.warn(result.toString());
		}
		return result;
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {  
        return "error/403";
    }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}
	
	/**
	 * 序列化对象为json,且不输出page对象
	 * @param o	序列化对象
	 * @return	json
	 */
	public String toJson(BaseModel<?> o) {
		return StringUtils.toJson(o);
	}
	
	public String toJson(Object o) {
		return StringUtils.toJson(o);
	}
	
	
	
	/**
	 * 序列化json时是否输出page属性
	 * @param o	输出对象
	 * @param t	true输出page, false不输出page属性
	 * @return	json
	 */
	public String toJson(BaseModel<?> o, boolean t) {
		return StringUtils.toJson(o, t);
	}
	
	/**
	 * 通过http发起Post请求
	 * @param url	url
	 * @param params 请求参数
	 * @return	返回报文
	 */
	protected String renderPost(String url, Map<String, Object> params) {
		String result = HttpUtils.doPost(Const.SOCIAL_SERVICE_URL +  url, params);
		return isErrorJson(result);
	}
	
	private String isErrorJson(String result) {
		if (StringUtils.isEmpty(result) || result.equals(HttpUtils.ERROR)) {
			ResultJson json = new ResultJson(ResultJson.Flag_404, "找不到资源!");
			return this.toJson(json);
		}
		return result;
	}
}
