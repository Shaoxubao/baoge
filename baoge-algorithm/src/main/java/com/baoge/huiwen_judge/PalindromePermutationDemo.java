package com.baoge.huiwen_judge;

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
            int r = getCharNumber(c);
            if (r != -1) {
                table[r]++;
            }
        }

        return table;
    }

    // 将每个字符对应为一个数字，a——>0,b——1,c——>2等等。不区分大小写，非字母对应为-1
    private static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');

        int val = Character.getNumericValue(c);

        if (a <= val && val <= z) {
            return val - a;
        }

        return -1;
    }


    /**
     * ========解法二
     */
    public static boolean isPermutationOfPalindrome2(String phrase) {
        int countOdd = 0;

        int[] table = new int[Character.getNumericValue('z')
                - Character.getNumericValue('a') + 1];

        for (char c : phrase.toCharArray()) {
            int num = getCharNumber(c);
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
            int x = getCharNumber(c);
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
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }

        return bitVector;
    }

    // 检测只有1个比特位被设置，将整数减1，并将其与原数值做&操作
    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

}
