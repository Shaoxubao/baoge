package com.baoge.jvm.memory;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/8
 *
 * jmap -clstats pid  查看类加载器信息
 * jmap -heap pid     堆信息
 * jstat -gc pid 打印元空间信息(MC MU)
 * jcmd PID GC.class_stats 一个新的诊断命令，用来连接到运行的 JVM 并输出详尽的类元数据的柱状图。
 *
 */
public class MyTest5 {
    public static void main(String[] args) {
        for (; ;) {
            System.out.println("hello world");
        }
    }

}
