package com.baoge.jvm.classloader;

import java.util.Random;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/17
 *
 * 当一个接口在初始化时，并不要求其父接口都完成初始化
 * 只有在真正使用到父接口的时候（如引用接口所定义的常量时），才会初始化
 *
 */
public class MyTest5 {

    public static void main(String[] args) {
//        System.out.println(MyChild5.b);
        System.out.println(MyChild5_1.thread);
    }

}

//interface MyParent5 {
//    public static final  int a = 5;
////    public static final  int a = new Random().nextInt(6);
//}

//interface MyChild5 extends MyParent5 {
////    public static final int b = 3;
//
//    public static final int b = new Random().nextInt(6);
//}

//////////////////////在初始化一个类时，并不会初始化它所实现的接口//////////////////////

interface MyParent5 {
    public static Thread thread = new Thread() {
        {
            System.out.println("Myparent5 invoked");
        }
    };
}

class MyChild5 implements MyParent5 {
    public static int b = 8;
}

//////////////////////在初始化一个接口时，并不会初始化它的父接口/////////////////////
interface MyParent5_1 {
    public static Thread thread = new Thread() {
        {
            System.out.println("Myparent5_1 invoked");
        }
    };
}

interface MyChild5_1 extends MyParent5_1 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyChild5_1 invoked");
        }
    };
}