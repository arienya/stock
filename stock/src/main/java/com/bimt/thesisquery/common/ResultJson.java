package com.bimt.thesisquery.common;


import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * JSON数据对象
 */
public class ResultJson implements Serializable {

	private static final long serialVersionUID = 1L;
	public final static int Flag_200 = 200;	// 成功
	public final static int Flag_404 = 404;	// 找不到资源
	public final static int Flag_500 = 500;	// 服务器端错误
	public final static int Flag_300 = 300;	// 客户端错误
	public final static int Flag_501 = 501;	// 身份认证失败
	public final static int Flag_502 = 502;	// 没有权限
	public final static int Flag_503 = 503;	// 业务失败
	public final static int Flag_504 = 504;	// 无社交帐号

    /**
     * 操作结果标识（系统级别的成功标识）
     */
    private int code = Flag_500;

    /**
     * 消息
     */
    private String message;

    /**
     * JSON业务数据结果
     */
    private Object result;


    public ResultJson() {

    }
	
	public ResultJson(int code) {
		this.code = code;
	}
	
	public ResultJson(int code, String message) {
		this.code = code;
		this.message = message;
	}

    public String getMessage() {
    	if (!StringUtils.isEmpty(message)) {
    		return message;
    	}
    	if (code == Flag_200) {
			message = "操作成功!";
		} else if (code == Flag_501) {
			message = "身份认证失败，请登录!";
		} else if (code == Flag_404) {
			message = "找不到资源!";
		} else if (code == Flag_500) {
			message = "服务器端错误!";
		} else if (code == Flag_502) {
			message = "没有权限!";
		} else if (code == Flag_503) {
			message = "业务失败!";
		} else if (code == Flag_504) {
			message = "无社交帐号!";
		} else {
			message = "操作失败!";
		}
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String toString() {
        return JSON.toJSONString(this);
    }

	public static void main(String[] args) {
		ResultJson r = new ResultJson();
		System.out.println(r.toString());
	}

    public static ResultJson ok() {
        ResultJson resultJson = new ResultJson(ResultJson.Flag_200);
        return  resultJson;
    }

    public static ResultJson ok(Object result) {
        ResultJson resultJson = new ResultJson(ResultJson.Flag_200);
        resultJson.setResult(result);
        return resultJson;
    }

    public static ResultJson error() {
        ResultJson resultJson = new ResultJson(ResultJson.Flag_500);
        return resultJson;
    }
    
    public static ResultJson error(String message) {
        ResultJson resultJson = new ResultJson(ResultJson.Flag_500);
        resultJson.setMessage(message);
        return resultJson;
    }

    public static ResultJson error(int code,String message) {
        ResultJson resultJson = new ResultJson(code,message);
        return resultJson;
    }

}
