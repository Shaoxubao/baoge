package com.baoge.jvm.classloader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/17
 *
 * 对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcom.baoge.jvm.classloader.MyParent4
 * 这种形式，动态生成的类型其父类就是Object。
 *
 * 对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型。
 *
 * 反编译class文件可以看到：
 * 助记符：
 * anewarray:创建一个引用类型的数组，并将其引用值压入栈顶
 * newarray:创建一个指定的基本类型（如：int，float，char等）的数组，并将其引用值压入栈顶
 */
public class MyTest4 {

    public static void main(String[] args) {
//        MyParent4 myParent4 = new MyParent4();
//        MyParent4 myParent5 = new MyParent4();  // 输出一次：MyParent4 static block


        // 非主动使用
        MyParent4[] myParent4s = new MyParent4[4]; // 不会输出“MyParent4 static block”
        System.out.println(myParent4s.getClass());

        MyParent4[][] myParent4s1 = new MyParent4[1][1];
        System.out.println(myParent4s1.getClass());

        System.out.println(myParent4s.getClass().getSuperclass());
        System.out.println(myParent4s1.getClass().getSuperclass());

        System.out.println("=====================");

        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());

        char[] chars = new char[1];
        System.out.println(chars.getClass());

        boolean[] booleans = new boolean[1];
        System.out.println(booleans.getClass());

        short[] shorts = new short[1];
        System.out.println(shorts.getClass());

        byte[] bytes = new byte[1];
        System.out.println(bytes.getClass());

    }

}

class MyParent4 {
    static {
        System.out.println("MyParent4 static block");
    }
}
