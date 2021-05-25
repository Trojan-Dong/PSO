package com.trojan.util;

public class StringUtils {

	public static boolean strIsNull(String str) {
		if(str==null||"".equals(str)||"null".equals(str)||str.length()==0) {
			return true;
		}
		return false;
	}
}
