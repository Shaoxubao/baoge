package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/21
 */

class Parent2 {
    static int a = 2;

    static {
        System.out.println("Parent2 static block");
    }
}

class Child2 extends Parent2 {
    static int b = 3;

    static {
        System.out.println("Child2 static block");
    }
}


public class MyTest10 {

    static {
        System.out.println("MyTest10 static block");
    }

    public static void main(String[] args) {
        Parent2 parent2;

        System.out.println("===========");

        parent2 = new Parent2();

        System.out.println("===========");

        System.out.println(parent2.a);

        System.out.println("===========");

        System.out.println(Child2.b);
    }

}
