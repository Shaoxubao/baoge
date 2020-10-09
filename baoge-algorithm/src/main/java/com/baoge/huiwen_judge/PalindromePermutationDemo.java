package com.baoge.huiwen_judge;

import com.baoge.utils.CharUtils;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/8
 *
 * 判断是否为回文中的一个排列
 */
public class PalindromePermutationDemo {
    public static void main(String[] args) {
        String str = "aba";

        System.out.println(isPermutationOfPalindrome1(str));
        System.out.println(isPermutationOfPalindrome2(str));
        System.out.println(isPermutationOfPalindrome3(str));
    }

    /**
     * ========解法一(散列表)
     */
    public static boolean isPermutationOfPalindrome1(String phrase) {

        int[] table = buildCharFrequencyTable(phrase);

        return checkMaxOneOdd(table);
    }

    // 检查最多一个字符的数目为奇数
    private static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }

                foundOdd = true;
            }
        }

        return true;
    }

    // 对字符出现的次数计数
    private static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') -
                Character.getNumericValue('a') + 1];

        for (char c : phrase.toCharArray()) {
            int r = CharUtils.getCharNumber(c);
            if (r != -1) {
                table[r]++;
            }
        }

        return table;
    }


    /**
     * ========解法二
     */
    public static boolean isPermutationOfPalindrome2(String phrase) {
        int countOdd = 0;

        int[] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a') + 1];

        for (char c : phrase.toCharArray()) {
            int num = CharUtils.getCharNumber(c);
            if (num != -1) {
                table[num]++;
                if (table[num] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }

        return countOdd <= 1;
    }

    /**
     * ========解法三
     */
    public static boolean isPermutationOfPalindrome3(String phrase) {
        int bitVector = createBitVector(phrase);

        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    // 创建一个字符串对应的字节数组，对于每个值为i的字符，翻转第i位字节
    private static int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = CharUtils.getCharNumber(c);
            bitVector = toggle(bitVector, x);
            System.out.println("========================bitVector: " + bitVector);
            System.out.println("==============bitVector(toBinary): " + Integer.toBinaryString(bitVector));
        }

        return bitVector;
    }

    // 翻转整数中第i位字节
    private static int toggle(int bitVector, int index) {
        if (index < 0) {
            return bitVector;
        }

        int mask = 1 << index;
        System.out.println("---------------------------index: " + index);
        System.out.println("----------------------------mask: " + mask);
        System.out.println("------------------mask(toBinary): " + Integer.toBinaryString(mask));
        System.out.println("-----------------------bitVector: " + bitVector);
        System.out.println("-------------bitVector(toBinary): " + Integer.toBinaryString(bitVector));

        if ((bitVector & mask) == 0) {
            bitVector |= mask;  // 不同字符字节位置为1
        } else {
            bitVector &= ~mask; // 不同字符字节位保留，相同字符字节位还原，比如a第一次的字节位1，a第二次来时将此字节位置为0
        }

        return bitVector;
    }

    // 检测只有1个比特位被设置，将整数减1，并将其与原数值做&操作
    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

}
