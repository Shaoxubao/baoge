package com.baoge.unique_chars;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/9/28
 */
public class UniqueChars {

    public static void main(String[] args) {

//        String str = "abc";
//        int val = str.charAt(1);
//        char valChar = str.charAt(1);
//        System.out.println(val);
//        System.out.println(valChar);


        System.out.println(isUniqueChars("abcda"));
        System.out.println(isUniqueChars2("abcda"));


    }

    public static boolean isUniqueChars(String str) {
        if (str == null) {
            return true;
        }

        if (str.length() > 128) {
            return false;
        }

        boolean[] charArray = new  boolean[128];

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (charArray[val]) {
                return false;
            }

            charArray[val] = true;
        }



        return true;
    }

    // 假定字符串只含有小写字母a到z。这样一来只需使用一个int型变量
    public static boolean isUniqueChars2(String str) {
        int flag = 0;

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((flag & (1 << val)) != 0) { // 用&来判断该位置上是否为1(<<左移不改变原来的数值。移位的结果是：第一个操作数乘以2的幂，指数的值是由第二个数给出的。右边空出用0填补.在不溢出的情况下,每左移一位相当于乘以2)
                return false;
            }

            flag |= (1 << val); // 在位移的位置上变换为1
        }

        return true;
    }

}
