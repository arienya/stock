package com.bimt.thesisquery.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 比美特中文姓名生成规则
 * 
 * @author jishu01
 *
 */
public class BimtNameRule {
	public static String[] getPinYinArray(String name) {
		if (!PubFun.isChinese(name)) {
			return new String[]{};
		}
		
		if (PubFun.isNotNull(name)) {
			name = name.replaceAll("\\s", "");
		}
		List<String> rList = new ArrayList<String>();
		String[] pys = PinYinUtils.getPinYinArray(name);
		if (pys.length < 3) {
			String[] tmpArray = new String[3];
			System.arraycopy(pys, 0, tmpArray, 0, 2);
			System.arraycopy(new String[] { " " }, 0, tmpArray, 2, 1);
			pys = tmpArray;
		}
		addToList(rList, format("%s %s-%s", pys));
		addToList(rList, format("%s %s%s", pys[0], pys[1], pys[2].toLowerCase()));
		addToList(rList, format("%s %s%s", pys[0], PinYinUtils.getFirstWord(pys[1]), PinYinUtils.getFirstWord(pys[2])));
		addToList(rList, format("%s%s %s", PinYinUtils.getFirstWord(pys[1]), PinYinUtils.getFirstWord(pys[2]),pys[0]));
		addToList(rList, format("%s%s %s", pys[1], pys[2].toLowerCase(), pys[0]));
		return rList.toArray(new String[] {});
	}

	/**
	 * 处理List
	 * 
	 * @param rList
	 * @param value
	 */
	private static void addToList(List<String> rList, String value) {
		if (!rList.contains(value)) {
			rList.add(value);
		}
	}

	/**
	 * 格式化字符串
	 * 
	 * @param pattern
	 * @param args
	 * @return
	 */
	private static String format(String pattern, Object... args) {
		String rStr = String.format(pattern, args);
		return rStr.replaceAll("[-]+[\\s]+|\\s$|^\\s", "").replaceAll("[\\s]{2}", " ");
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(getPinYinArray("徐锋")));
		System.out.println(Arrays.toString(getPinYinArray("张永乐")));
//		System.out.println(Arrays.toString(getPinYinArray("张     永 le")));
	}
}
