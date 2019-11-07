package com.baoge.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author shaoxubao
 * @Date 2019/11/7 14:38
 */
public class DigitUtil {
    /**
     * 判断一个字符串是否含有数字
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断一个字符串是否都为数字
     */
    public static boolean isAllDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(strNum);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(isAllDigit("34556436547"));
    }
}
