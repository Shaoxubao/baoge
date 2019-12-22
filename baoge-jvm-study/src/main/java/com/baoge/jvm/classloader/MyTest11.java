package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/22
 */

class Parent3 {
    static int a = 3;

    static {
        System.out.println("Parent3 static block");
    }

    static void doSomething() {
        System.out.println("do something...");
    }
}

class Child3 extends Parent3 {
    static {
        System.out.println("Child3 static block");
    }
}

public class MyTest11 {

    public static void main(String[] args) {
        System.out.println(Child3.a);  // 对父类的主动使用（变量a是定义在父类的静态变量），并不是对Child3的主动使用，所有不会初始化Child3
        System.out.println("===============");
        Child3.doSomething();
    }

}
