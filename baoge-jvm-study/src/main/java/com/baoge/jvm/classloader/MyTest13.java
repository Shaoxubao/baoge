package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/22
 */
public class MyTest13 {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        while (classLoader != null) {
            classLoader = classLoader.getParent();

            System.out.println(classLoader);
        }
    }

}
