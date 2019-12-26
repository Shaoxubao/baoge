package com.baoge.jvm.classloader.demo_1;

import com.baoge.jvm.classloader.MyTest16_MyClassLoader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/26
 */
public class MyTest17 {

    public static void main(String[] args) throws Exception {
        MyTest16_MyClassLoader loader1 = new MyTest16_MyClassLoader("loader1");

        Class<?> clazz = loader1.loadClass("com.baoge.jvm.classloader.demo_1.MySample");
        System.out.println("class:" + clazz.hashCode());

        // 如果注释此行，则不会实例化MySample类，只会将MySample类加载到虚拟机内存，不会加载MyCat
//        Object object = clazz.newInstance();

    }

}
