package com.baoge.jvm.bytecode;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/5
 *
 * 编译成字节码，观察字节码
 * javap -c baoge-jvm-study/src/main/java/com/baoge/jvm/bytecode/MyTest1
 * javap -verbose baoge-jvm-study/src/main/java/com/baoge/jvm/bytecode/MyTest1
 * 再用16进制工具查看编译之后的.class文件
 */
public class MyTest1 {

    private int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
