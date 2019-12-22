package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/22
 */
public class MyTest15 {

    public static void main(String[] args) {

        String[] strings = new String[2];
        System.out.println(strings.getClass());

        System.out.println(strings.getClass().getClassLoader()); // 输出null,根类加载器

        System.out.println("============");

        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(myTest15s.getClass().getClassLoader());

        System.out.println("============");

        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader()); // 输出null,原生类型没有类加载器

    }

}
