package com.baoge.repalce_space;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/8
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        String str = "I am john";
        System.out.println(replaceSpace(str.toCharArray(), 9));
    }


    public static char[] replaceSpace(char[] input, int trueLength) {
        if (input == null || input.length <= 0) {
            return null;
        }

        // 计算空格数量
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (input[i] == ' ') {
                spaceCount++;
            }
        }

        // 将空格用%20替换
        int index = trueLength + spaceCount * 2; // 字符串总长度
        char[] newStr = new char[index];         // 创建一个新的最后返回的数组
        for (int i = trueLength - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                newStr[index - 1] = '0';
                newStr[index - 2] = '2';
                newStr[index - 3] = '%';

                index = index - 3;
            } else {
                newStr[index - 1] = input[i];
                index--;
            }
        }

        return newStr;
    }

}
