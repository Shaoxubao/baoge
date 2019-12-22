package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/21
 */

class FinalTest {
    public static final int a = 3;

    static {
        System.out.println("Final static block");
    }
}

public class MyTest8 {

    public static void main(String[] args) {
        System.out.println(FinalTest.a);
    }

}
