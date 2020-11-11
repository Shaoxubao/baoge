package com.baoge.test.statictest;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/23
 */
public class Test {

    public static void main(String[] args) {

        float f = 42.0f;
        float f1[] = new float[2];
        float f2[] = new float[2];
        float f3[] = f1;
        long x = 42;
        f1[0] = 42.0f;

        System.out.println(f1 == f2);     // false
        System.out.println(x == f1[0]);   // true
        System.out.println(f1 == f3);     // true
//        System.out.println(f2 == f1[1]);  // false

        Integer a = new Integer(42);
        Integer b = new Integer(42);
        int c = 42;
        System.out.println(a == c);
        System.out.println(a == b);
        System.out.println(new Integer(42) == new Integer(42));

    }
}
