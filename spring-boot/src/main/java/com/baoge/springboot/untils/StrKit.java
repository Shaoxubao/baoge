package com.baoge.springboot.untils;

import org.apache.commons.lang.StringUtils;

public class StrKit {
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}

	public static boolean notBlank(String str) {
		return str == null || "".equals(str.trim()) ? false : true;
	}

	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}
	
	/**
	 * 获取文件名的后缀名 带"."
	 * @param filename
	 * @return
	 */
	public static String getFileSuffix(String filename){
		int index = filename.lastIndexOf(".") ;
		return index == -1 ? "" : filename.substring(index);
	}
	
	/**
	 * 去掉指定匹配字符串中最后一个指定的字符
	 * @param srcStr
	 * @param lastStr
	 * @return
	 */
	public static String substringLastStr(String srcStr,String lastStr) {
		if(StringUtils.isNotEmpty(srcStr)) {
			int index = srcStr.lastIndexOf(lastStr);
			return index != - 1 ? srcStr.substring(0,index) : srcStr;
		}
		return srcStr;
	}
	
}
