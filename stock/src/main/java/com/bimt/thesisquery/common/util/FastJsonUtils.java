package com.bimt.thesisquery.common.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastjson工具类
 * 
 * @author xufeng
 *
 */
public class FastJsonUtils {

	/**
	 * 功能描述：把JSON数据转换成普通字符串列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<String> getStringList(String jsonData) throws Exception {
		return JSON.parseArray(jsonData, String.class);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> T getSingleBean(String jsonData, Class<T> clazz) throws Exception {
		return JSON.parseObject(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> List<T> getBeanList(String jsonData, Class<T> clazz) throws Exception {
		return JSON.parseArray(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的java对象列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<Map<String, Object>> getBeanMapList(String jsonData) throws Exception {
		return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
		});
	}

	/**
	 * 将网络请求下来的数据用fastjson处理空的情况，并将时间戳转化为标准时间格式
	 * 
	 * @param result
	 * @return
	 */
	public static String dealResponseResult(String result) {
		result = JSONObject.toJSONString(result, SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteEnumUsingToString,
				SerializerFeature.WriteSlashAsSpecial, SerializerFeature.WriteTabAsSpecial);
		return result;
	}
	
	/**
	 * 将对象转为json字符串
	 * @param o
	 * @return
	 */
	public static String toJson(Object o) {
		return JSON.toJSONString(o);
	}
	
	/**
	 * 将对象转为json字符串，过滤掉page属性
	 * @param o
	 * @return
	 */
	public static String toJsonPageEx(Object o) {
		return toJson(o, "page");
	}

	/**
	 * 对象转json,并过滤掉对象中属性名为filterName的属性
	 * @param o	需转为json的对象
	 * @param filterName
	 * @return
	 */
	public static String toJson(Object o, final String filterName) {
		PropertyFilter p = new PropertyFilter(){
			@Override
			public boolean apply(Object object, String name, Object value) {
				if (name.equals(filterName)) {
					return false;
				}
				return true;				
			}			
		};
		return JSON.toJSONString(o, p);
	}
}
