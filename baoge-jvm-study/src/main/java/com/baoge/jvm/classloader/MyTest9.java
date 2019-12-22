package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/21
 */

class Parent {
    static int a = 3;

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent {
    static int b = 4;

    static {
        System.out.println("Child static block");
    }
}

public class MyTest9 {

    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }

}
