package com.baoge.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/7
 *
 * 虚拟机栈：Stack Frame 栈帧
 * 程序计数器（Program Counter）：
 * 本地方法栈（native）：主要用于执行本地方法
 * 堆（Heap）：JVM管理的最大的一块内存。与堆相关是垃圾收集器，现在几乎所有的垃圾收集器都是采用的
 *              分代收集算法，所以，堆空间也基于这一点进行了相应的划分：新生代与老年代，Eden空间，
 *              From Survivor空间和To Survivor空间。
 * 方法区（Method Area）：存储元信息。永久代（Permanent Generation）,从JDK1.8开始彻底废弃了永久代，使用元空间(meta space)
 * 运行时常量池：方法区的一部分内容
 * 直接内存：Direct Memory,与Java NIO密切相关。JVM是通过堆上的DirectBuffer来操作直接内存。
 *
 * 关于Java对象创建的过程（详见笔记有道云）：
 * new关键字创建对象的3个步骤：
 * 1、在堆内存中创建出对象的实例。
 * 2、为对象的实例成员变量【而非静态成员变量】赋初值。
 * 3、将对象的引用返回。
 * 指针碰撞（前提是堆中的空间通过一个指针进行分割，一侧是已经被占用的空间，另一侧是未被占用的空间）
 * 空闲列表（前提是堆内存空间中已被使用与未被使用的空间是交织在一起的，这时，虚拟机就需要通过一个列表来记录哪些空间是可以使用的，哪些空间是已被使用的，
 *          接下来找出可以容纳下新创建对象的且未被使用的空间，在此空间存放该对象，同时还要修改列表上的记录）
 * 对象在内存中的布局【了解既可】：
 * 对象的内存布局其实就是指一个对象它存放的信息有啥， 总共分为三部分：
 * 1、对象头。
 * 它会存放对象自身的一些运行时的数据信息，比如说一个对象有一个hash码、还有分代的一个信息等，把这些信息都放置在对象头里面。
 * 2、实例数据 （既我们在一个类中所声明的各项信息）。如成员变量。
 * 3、对齐填充（可选），其实就是起到一些点位符的作用，比如说要求8的倍数，如果不够8的话被0等。
 *
 * 引用访问对象的方式：
 * 1、使用句柄的方式。
 * 2、使用直接指针的方式。
 *
 * 转储：
 * -Xmx5M -Xms5M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */
public class MyTest1 {

    public static void main(String[] args) {
        List<MyTest1> list = new ArrayList<MyTest1>();
        for (; ; ) {
            list.add(new MyTest1());

            System.gc();
        }
    }

}
