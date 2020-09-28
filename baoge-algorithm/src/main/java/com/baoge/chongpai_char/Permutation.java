package com.baoge.chongpai_char;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/9/28
 */
public class Permutation {

    public static void main(String[] args) {
        System.out.println(permutation("abcd", "cdbae"));
    }

    public static boolean permutation(String source, String target) {

        if (source == null && target == null) {
            return true;
        }

        if (source == null || target == null) {
            return false;
        }

        int[] letters = new int[128]; // 假设为ASCII字符
        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i);
            letters[s]++;
        }

        for (int i = 0; i < target.length(); i++) {
            int t = target.charAt(i);
            letters[t]--;
            if (letters[t] < 0) {
                return false;
            }
        }

        return true;
    }

}
