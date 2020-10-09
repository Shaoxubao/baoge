package com.baoge.huiwen_judge;

import com.baoge.utils.CharUtils;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/9
 *
 * 翻转整数中第i位字节
 */
public class BitToggle {

    public static void main(String[] args) {
        //         a         b       c
        // index   0         1       2

        int mask_a = 1 << 0;  // 1 ——> 0001
        int mask_b = 1 << 1;  // 2 ——> 0010
        int mask_c = 1 << 2;  // 4 ——> 0100
        System.out.println("mask_a: " + mask_a + ", mask_b: " + mask_b + ", mask_c: " + mask_c);


        int _mask_a = ~mask_a;  // 1 ——> 1110 负数(补码)，欲求其源码，需先对其取反，然后再加1，结果为：-2
        int _mask_b = ~mask_b;  // 2 ——> 1101
        int _mask_c = ~mask_c;  // 4 ——> 1011
        System.out.println("_mask_a: " + _mask_a + ", _mask_b: " + _mask_b + ", _mask_c: " + _mask_c);
        System.out.println("_mask_a: " + Integer.toBinaryString(_mask_a)
                + ", _mask_b: " + Integer.toBinaryString(_mask_b)
                + ", _mask_c: " + Integer.toBinaryString(_mask_c));

    }

    // 创建一个字符串对应的字节数组，对于每个值为i的字符，翻转第i位字节
    private static int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = CharUtils.getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }

        return bitVector;
    }

    // 翻转整数中第i位字节，以字符串aba为例：
    // 1、当bitVector = 0, index = 0(a) 时：
    //    mask = 1(0001)
    //   (bitVector & mask) == 0成立
    //   bitVector |= mask (0000 | 0001) 结果为：bitVector = 1(0001)

    // 2、当bitVector = 1(0001), index = 1(b) 时：
    //    mask = 2(0010)
    //   (bitVector & mask) == 0成立
    //    bitVector |= mask (0001 | 0010) 结果为：bitVector = 3(0011)

    // 1、当bitVector = 3(0011), index = 0(a) 时：
    //    mask = 1(0001)
    //   (bitVector & mask) == 0不成立
    //   ~mask结果为：-2(1110)
    //   bitVector &= ~mask (0011 | 1110) 结果为：bitVector = 2(0010)
    private static int toggle(int bitVector, int index) {
        if (index < 0) {
            return bitVector;
        }

        int mask = 1 << index; // 1 ——> 1110, 2 ——> 1101, 4 ——> 0100

        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }

        return bitVector;
    }
}
