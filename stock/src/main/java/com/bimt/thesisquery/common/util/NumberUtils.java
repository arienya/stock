package com.bimt.thesisquery.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang3.RandomStringUtils;

public class NumberUtils {

	private static final int DEF_DIV_SCALE = 2;

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isNumberic(String num) {
		return (null == num || num.length() <= 0 || num.matches("\\d{1,}")) ? true : false;
	}

	/**
	 * @return 返回12位随机数
	 */
	public static String randomNumber() {
		return RandomStringUtils.randomNumeric(12);
	}

	/**
	 * @param parm
	 * @return 返回指定位数随机数
	 */
	public static String randomNumber(int parm) {
		return RandomStringUtils.randomNumeric(parm);
	}

	/**
	 * * 两个Double数相加 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.add(b2).doubleValue());
	}

	/**
	 * * 两个Double数相减 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.subtract(b2).doubleValue());
	}

	/**
	 * * 两个Double数相乘 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.multiply(b2).doubleValue());
	}

	/**
	 * * 两个Double数相除 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * * 两个Double数相除，并保留scale位小数 *
	 * 
	 * @param v1
	 *            *
	 * @param v2
	 *            *
	 * @param scale
	 *            *
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * @param v1
	 * @return 返回指定Double的负数
	 */
	public static Double neg(Double v1) {
		return sub(new Double(0), v1);
	}

	/**
	 * 保留double小数点后几位，会四舍五入
	 * @param s	转换值
	 * @param index	保留位数
	 * @return
	 */
	public static double holdPoint(double s, int index) {
		BigDecimal b = new BigDecimal(s);
		return b.setScale(index, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 将  2.43%转为 float
	 * @param s
	 * @return
	 */
	public static float bai2double(String s) {
		NumberFormat nf=NumberFormat.getPercentInstance();
		Number m = 0;
		try {
			m = nf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m.floatValue();
	}

	public static void main(String[] args) {
//		System.out.println(holdPoint(21.310350000000002, 4));
		System.out.println(bai2double("-2.34%"));
	}

}
