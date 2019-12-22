package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/22
 */

class CL {
    static {
        System.out.println("CL static block");
    }
}

public class MyTest12 {

    public static void main(String[] args) throws Exception {
        // 调用ClassLoader的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Class<?> clazz = classLoader.loadClass("com.baoge.jvm.classloader.CL");

        System.out.println(clazz);
        System.out.println("============");

        clazz = Class.forName("com.baoge.jvm.classloader.CL");

        System.out.println(clazz);
    }
}
