package com.baoge.jvm.classloader;

import java.net.URL;
import java.util.Enumeration;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/22
 */
public class MyTest14 {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        String resourceName = "com/baoge/jvm/classloader/MyTest13.class";

        Enumeration<URL> urls = classLoader.getResources(resourceName);

        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        System.out.println("================");

//        Class<?> clazz = MyTest14.class;
        Class<?> clazz = String.class;
        System.out.println(clazz.getClassLoader());
    }

}
