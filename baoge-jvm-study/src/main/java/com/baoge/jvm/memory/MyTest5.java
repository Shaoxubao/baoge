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
 * jcmd PID VM.flags 查看jvm设置的参数(jvm启动参数)，如最大最小堆，gc收集器等
 * jcmd PID help 查看可对当前进程进行如下参数操作
 * jcmd PID VM.uptime 查看JVM的启动时长
 * jcmd PID GC.class_histogram 查看系统中类的统计信息
 * jcmd PID Thread.print 查看当前线程堆栈信息
 * jcmd PID GC.heap_dump [文件路径名]  导出堆栈信息
 * jcmd PID VM.system_propertis 查看JVM的属性信息
 * jcmd PID VM.command_line JVM启动时命令参数
 *
 * jstack:可以查看或者导出Java应用程序中线程的堆栈信息
 * jstack PID:打印线程堆栈信息
 *
 * jmc:Java Misson Control 实时获取Java运行程序信息
 *
 * jhat:分析堆转储文件
 */
public class MyTest5 {
    public static void main(String[] args) {
        for (; ;) {
            System.out.println("hello world");
        }
    }

}
