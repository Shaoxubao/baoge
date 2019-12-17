package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/18
 *
 * 类的准备阶段和初始化阶段的重要意义
 */
public class MyTest6 {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();

        System.out.println("counter1:" + Singleton.counter1);
        System.out.println("counter2:" + Singleton.counter2);
    }

}

class Singleton {

    public static int counter1;


    private static Singleton singleton = new Singleton();
    private Singleton() {
        counter1++;
        counter2++; // 准备阶段的重要意义

        System.out.println("==counter1:" + counter1);
        System.out.println("==counter2:" + counter2);
    }

    public static int counter2 = 0; // 此句放到这结果是不一样的

    public static Singleton getInstance() {
        return singleton;
    }

}
