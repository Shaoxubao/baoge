package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/16
 *
 * 加final的常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中，
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化
 *
 * 注意：这里指的是将常量存放到了MyTest2的常量池中，之后MyTest2与MyParent2就没有任何关系了
 * 甚至，我们可以将MyParent2的class文件删除
 *
 * ///////////////////////
 * 助记符：
 * ldc表示将int，float或是String类型的常量值从常量池中推送至栈顶
 * bipush表示将单字节（-128 - 127）的常量值推送至栈顶
 * sipush表示将一个短整型常量值（-32768 - 32767）推送至栈顶
 * iconst_1表示将int类型1推送至栈顶 （iconst_1 - iconst_5）
 *
 */
public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.str); // str不加final修饰输出：MyParent2 static block
                                           //                     hello world
                                           // str加final修饰输出： hello world
    }
}

class MyParent2 {
//    public static String str = "hello world";
    public static final String str = "hello world";

    public static final short s = 8;

    public static final int i = 128;

    static {
        System.out.println("MyParent2 static block");
    }
}

/**
 * javap -c MyTest2.class 输出：
 *public class com.baoge.jvm.classloader.MyTest2 {
 *   public com.baoge.jvm.classloader.MyTest2();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #4                  // String hello world
 *        5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: return
 * }
 *
 *
 * System.out.println(MyParent2.s);时
 * javap -c MyTest2.class 输出：
 * public class com.baoge.jvm.classloader.MyTest2 {
 *   public com.baoge.jvm.classloader.MyTest2();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: bipush        8
 *        5: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 *        8: return
 * }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
