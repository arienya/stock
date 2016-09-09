package com.bimt.thesisquery.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PinYinUtils {
	/**
	 * 获取字符串拼音全拼
	 * 
	 * @param name
	 * @return
	 */
	public static String getPinYin(String name) {
		char[] namec = name.toCharArray();
		String[] names = new String[namec.length];
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		String rStr = "";
		int t0 = namec.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(namec[i]).matches("[\\u4E00-\\u9FA5]+")) {
					names = PinyinHelper.toHanyuPinyinStringArray(namec[i], format);
					rStr += names[0];
				} else
					rStr += java.lang.Character.toString(namec[i]);
			}
			// System.out.println(t4);
			return rStr;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return rStr;
	}

	public static String[] getPinYinArray(String name) {
		List<String> lists = new ArrayList<String>();

		char[] namec = name.toCharArray();
		String[] names = new String[namec.length];
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		int t0 = namec.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(namec[i]).matches("[\\u4E00-\\u9FA5]+")) {
					names = PinyinHelper.toHanyuPinyinStringArray(namec[i], format);
					lists.add(toFirstUpperCase(names[0]));
				} else {
					if (i == 0) {
						if (PubFun.isNotNull(names[0])) {
							lists.add(names[0]);
						}else{
							lists.add(String.valueOf(namec[0]));
						}
					} else if (PubFun.isChChar(String.valueOf(namec[i-1]))) {
						lists.add(toFirstUpperCase(String.valueOf(namec[i])));
					}else{
						lists.set(lists.size() - 1, lists.get(lists.size() - 1) + namec[i]);
					}
				}
			}
			return lists.toArray(new String[] {});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return lists.toArray(new String[] {});
	}

	/**
	 * 返回中文的首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 首字母大写
	 * 
	 * @param name
	 * @return
	 */
	public static String toFirstUpperCase(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 将字符串转移为ASCII码
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	/**
	 * 获取首字母
	 * 
	 * @param words
	 * @return
	 */
	public static String getFirstWord(String words) {
		return String.valueOf(words.toCharArray()[0]).toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(getPinYin("张永乐"));
		System.out.println(Arrays.toString(getPinYinArray("张永乐")));
	}
}
