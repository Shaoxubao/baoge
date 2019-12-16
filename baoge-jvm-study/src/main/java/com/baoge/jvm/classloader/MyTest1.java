package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/16
 *
 * 对于静态字段来说，只有直接定义了该字段的类才会被初始化
 *
 * 当初始化子类时，要求先初始化其全部父类
 *
 * -XX:+TraceClassLoading，用于追踪类的加载信息并打印出来
 *
 * -XX:+<option>, 表示开启option选项
 * -XX:-<option>, 表示关闭option选项
 * -XX:<option>=<value>, 表示将option选项的值设置为value
 */
public class MyTest1 {

    public static void main(String[] args) {
        System.out.println(MyChild1.str); // 输出：MyParent static block
                                          //      hello world

//        System.out.println(MyChild1.str2); // 注释上面sout，输出：MyParent static block
                                           //                   MyChild static block
                                           //                   welcome
    }

}

class MyParent1 {
    public static String str = "hello world";

    static {
        System.out.println("MyParent static block");
    }

}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";

    static {
        System.out.println("MyChild static block");
    }
}


