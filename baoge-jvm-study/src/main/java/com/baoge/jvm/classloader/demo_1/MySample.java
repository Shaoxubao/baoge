package com.baoge.jvm.classloader.demo_1;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/26
 */
public class MySample {

    public MySample() {
        System.out.println("MySample is loaded by:" + this.getClass().getClassLoader());

        // 定义此处，一个原因是加载MyCat的类加载器就是加载MySample类的类加载器
        new MyCat();

        // 此处输出MyCat.class，运行需先注释MyCat中输出的MySample.class，不然会报错，
        // 然后删除classpath下的MySample文件，运行程序不会报错，
        // 因为父类加载器加载的类在子类加载都可以看到
        System.out.println("from MySample:" + MyCat.class);
    }

}
