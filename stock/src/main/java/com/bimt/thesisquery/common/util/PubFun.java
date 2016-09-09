package com.bimt.thesisquery.common.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class PubFun {
	/**
	 * 获取key/value结构对象的value值
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static Object getValue(Object obj, String key) {
		if (obj instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) obj;
			if (map.containsKey(key)) {
				return map.get(key);
			} else {
				return "";
			}
		}
		return null;
	}

	/**
	 * 获取key/value结构对象的value值，并转化为字符串
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	public static String getStringValue(Object obj, String key) {
		return String.valueOf(getValue(obj, key));
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return true/false
	 */
	public static boolean isNull(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;
			return "".equals(s.trim()) || "null".equals(s)||s.trim().equals("null");
		} else {
			return obj == null;
		}
	}

	/**
	 * 判断对象是否不为空
	 * 
	 * @param obj
	 * @return true/false
	 */
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	/**
	 * 如果参数s1为空,则输出s2
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String ifNull(String s1, String s2) {
		if (PubFun.isNotNull(s1)) {
			return PubFun.isNull(PubFun.trim(s1)) ? s2 : s1;
		}
		return isNull(s1) ? s2 : s1;
	}

	/**
	 * 将数组转化为字符串
	 * 
	 * @param array
	 *            数组对象
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static String convertArrayToString(String[] array, String split) {
		StringBuffer sb = new StringBuffer();
		if (PubFun.isNotNull(array) && array.length > 0) {
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
				if (i < array.length - 1) {
					sb.append(split);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 将数组转化为字符串
	 * 
	 * @param array
	 *            数组对象
	 * @param split
	 *            分隔符
	 * @return
	 */
	public static String convertArrayToString(Object obj, String split) {
		StringBuffer sb = new StringBuffer();
		if (obj instanceof String[]) {
			String[] array = (String[]) obj;
			if (PubFun.isNotNull(array) && array.length > 0) {
				for (int i = 0; i < array.length; i++) {
					sb.append(checkStr(array[i], split));
					if (i < array.length - 1) {
						sb.append(split);
					}
				}
			}
		} else if (obj instanceof List) {
			List<String> list = (List<String>) obj;
			if (PubFun.isNotNull(list) && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					sb.append(list.get(i));
					if (i < list.size() - 1) {
						sb.append(split);
					}
				}
			}
		}
		return sb.toString();
	}

	private static String checkStr(String value, String split) {
		String tmpStr = ifNull(value, "");
		return (tmpStr.indexOf(split) != -1) ? tmpStr.replace(split, "##") : tmpStr;
	}

	/**
	 * 将数组转化为字符串,默认已逗号(,)拼接
	 * 
	 * @param array
	 *            数组
	 * @return 字符串
	 */
	public static String convertArrayToString(Object obj) {
		return convertArrayToString(obj, ",");
	}

	/**
	 * 获取类名称
	 * 
	 * @param o
	 * @return
	 */
	public static String getClassFileName(Object o) {
		return o.getClass().getSimpleName();
	}

//	/**
//	 * 检查最后一位是否有标点符号如果没有则添加省略号
//	 * 
//	 * @param text
//	 * @return
//	 */
//	public static String checkEndCharacter(String text) {
//		if (isNotNull(text)) {
//			if (!Pattern.compile(SysConst.END_CHARACTER_REGEX).matcher(text).find()) {
//				return text + "...";
//			}
//		}
//		return text;
//	}

//	/**
//	 * 去除字符串中的特殊字符
//	 * 
//	 * @param text
//	 * @return
//	 */
//	public static String removeCharacter(String text) {
//		if (PubFun.isNotNull(text)) {
//			return text.replaceAll(SysConst.WRITEAID_KEYWORD_REGEX, "");
//		}
//		return "";
//	}

//	/**
//	 * 将字符串转换为数组
//	 * 
//	 * @param text
//	 * @return
//	 */
//	public static String[] strToArray(String text) {
//		String _tmpText = removeCharacter(text).replaceAll("[，;；,#!&*@！￥%]", ",");
//		return _tmpText.split(",");
//	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("IF2006", "1");
		map.put("IF2016", "3");
		map.put("IF2017", "6");
		map.put("IF2008", "4");

		System.out.println(getMaxMapKeyValue(map));

		String name = "张永乐";

		String py = PinYinUtils.getPinYin(name);

		System.out.println(py);
		System.out.println(PinYinUtils.getCnASCII(name));
		System.out.println(PinYinUtils.getPinYinHeadChar(name));

		System.out.println(isChChar("l"));

//		showLog("test");

		List<String> a = new ArrayList<String>();
		a.add("15201087150");
		a.add("56d0f4d1e138230434742797");
		a.add("56d0f4d1e13823043474279e");

		if (a.contains("15201087150")) {
			System.out.println("11");
		} else {
			System.out.println("22");
		}

		// System.err.println(isChinese("A".charAt(0)));
		System.out.println(isChinese("发放大东方"));

//		System.out.println(Arrays.toString(strToArray("This is progress")));

		System.out.println((float) 0.01);

		DecimalFormat df = new DecimalFormat("######0.00");

		System.out.println(df.format(2.34343));
		
		System.out.println(Math.floor(2.155));
		
		System.out.println(isNull(" null"));

	}

	/**
	 * 获取Map中最大Key值
	 * 
	 * @param map
	 * @return
	 */
	public static String getMaxMapKey(Map<String, String> map) {
		Object[] obj = map.keySet().toArray();
		Arrays.sort(obj);
		return String.valueOf(obj[obj.length - 1]);
	}

	/**
	 * 获取Map中最大key值的value值
	 * 
	 * @return
	 */
	public static String getMaxMapKeyValue(Map<String, String> map) {
		return (map.size() < 1) ? "" : map.get(getMaxMapKey(map));
	}

	public static String toUppercase(String name_en) {
		if (isNotNull(name_en)) {
			return name_en.toUpperCase();
		}
		return "";
	}

	public static int compareStr(String p1, String p2) {
		if (Double.parseDouble(ifNull(p1, "0")) > Double.parseDouble(ifNull(p2, "0"))) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 将Vector对象转换为String[]对象
	 * 
	 * @param _keywords
	 * @return
	 */
	public static String[] changeToArray(Vector<String> _keywords) {
		String[] arrs = new String[] {};
		return _keywords.toArray(arrs);
	}

	/**
	 * 将指定 object对象转换为String[]
	 * 
	 * @param obj
	 * @return
	 */
	public static String[] toArray(Object obj) {
		String[] arrs = new String[] {};
		if (obj instanceof Vector) {
			Vector<String> vector = (Vector<String>) obj;
			return vector.toArray(arrs);
		} else if (obj instanceof Collection) {
			Collection<String> collection = (Collection<String>) obj;
			return collection.toArray(arrs);
		}
		return arrs;

	}

	public static String getUUID() {
		return UUID.randomUUID().toString().toUpperCase().replace("-", "");
	}

	public static boolean isChChar(String value) {
		if (java.lang.Character.toString(value.charAt(0)).matches("[\\u4E00-\\u9FA5]+")) {
			return true;
		} else {
			return false;
		}
	}

//	/**
//	 * 输出日志
//	 * 
//	 * @param log
//	 */
//	public static void showLog(Object log) {
//		Logger logger = LoggerFactory.getLogger(PubFun.class);
//		try {
//			StackTraceElement stack[] = Thread.currentThread().getStackTrace();
//			StackTraceElement traceElement = stack[2];
//
//			String callName = traceElement.getClassName();
//			String funcName = traceElement.getMethodName();
//			int lineNumber = traceElement.getLineNumber();
//
//			logger.info(
//					DateUtils.getFullTime() + "|" + callName.substring(callName.lastIndexOf(".") + 1, callName.length())
//							+ "|" + funcName + "|" + lineNumber + "|log=##" + log + "##");
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}

	/**
	 * 判断字符是否是中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
	}

	/**
	 * 判断一个字符串是否含有中文
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str) {
		if (str == null)
			return false;
		for (char c : str.toCharArray()) {
			if (isChinese(c))
				return true;// 有一个中文字符就返回
		}
		return false;
	}

	public static String removeLastDot(String dotString) {
		return removePunctuation(dotString, ".");
	}

	public static String removePunctuation(String text, String p) {
		if (isNotNull(text)) {
			if ((text.lastIndexOf(p) + 1) == text.length()) {
				return text.substring(0, text.length() - 1);
			}
		}
		return text;
	}

	public static String trim(String value) {
		return isNotNull(value) ? value.trim() : "";
	}

	public static String getValueFromArray(String[] times, int i) {
		return (times.length > i) ? times[i] : "";
	}

	public static String keep2Decimal(double d) {
		if (isNotNull(d)) {
			DecimalFormat df = new DecimalFormat("######0.00");
			df.setRoundingMode(RoundingMode.FLOOR);
			return String.valueOf(df.format(d));
		}
		return String.valueOf(d);
	}
	
	public static String keep2Decimal(String d) {
		if (isNotNull(d)) {
			DecimalFormat df = new DecimalFormat("######0.00");
			df.setRoundingMode(RoundingMode.FLOOR);
			return String.valueOf(df.format(Double.parseDouble(d)));
		}
		return String.valueOf(d);
	}

}
