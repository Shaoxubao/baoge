package com.baoge.jvm.classloader;

import java.util.UUID;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/17
 *
 * 当一个常量的值并非在编译期间可以确定的，那么其值就不会被放到调用类的常量池中，
 * 这时在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
 *
 */
public class MyTest3 {

    public static void main(String[] args) {
        System.out.println(MyParent3.str);  // 输出：MyParent3 static block
                                            //      a13887a0-f4fd-4f7e-8b24-88297e8dd208
    }

}

class MyParent3 {

    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static block");
    }
}
