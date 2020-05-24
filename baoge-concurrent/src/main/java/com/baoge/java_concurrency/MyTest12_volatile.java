package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/24
 *
 * volatile关键字：
 * 作用：
 * 1.实现long/double类型变量的原子操作
 * 2.防止指令重排序
 * 3.实现变量的可见性
 *
 * volatile double a = 1.0
 * 当使用volatile变量修饰变量时，应用不会从寄存器中获取该变量的值，而是从内存（高速缓存）中获取。
 *
 * volatile与锁类似的地方：
 * 1.确保变量的内存可见性
 * 2.防止指令重排序
 *
 * volatile可以确保对变量写操作的原子性，但不具备排他性
 * 另外重要一点在于：使用锁可能会导致线程上下文切换（内核态和用户态之间的切换），但使用volatile不会出现这种情况.
 *
 * 错误做法:
 * volatile a = b + 2
 * volatile a = a++
 *
 * 防止指令重排序与实现变量的可见性都是通过一种手段来实现的:内存屏障(memory barrier)
 *
 * int a =1;
 * String s = "hello";
 *
 * 内存屏障(Release Barrier,释放屏障)
 * volatile boolean v = false; // 写入操作
 * 内存屏障(Store Barrier,存储屏障)
 *
 * Release Barrier:防止下面的volatile与上面的所有操作的指令重排序;
 * Store Barrier: 作用时刷新处理器缓存,结果是可以确保该存储屏障之前一切的操作所生成的结果对于其他处理器来说都可见.
 *
 * 内存屏障(Load Barrier,加载屏障)
 * boolean v1 = v;
 * 内存屏障(Acquire Barrier,获取屏障)
 *
 * int a =1;
 * String s = "hello";
 *
 * Load Barrier:可以刷新处理器缓存,同步其他处理器对该volatile变量的修改结果;
 * Acquire Barrier:可以防止上面的volatile读操作与下面的所有操作语句的指令重排序.
 *
 * 对于volatile关键字的读写操作,本质上是通过内存屏障来执行的.
 * 内存屏障兼具了两个能力:1.防止指令重排序,2.实现变量内存的可见性.
 *
 * 1.对于读取操作来说,volatile可以确保该操作与其后续的所有读写操作都不会进行指令重排序;
 * 2.对于修改操作来说,volatile可以确保该操作与其上面的所有读写操作都不会进行指令重排序.
 *
 * volatile与锁的比较:
 * 锁同样具备变量内存可见性和防止指令重排序的功能.
 *
 * monitorenter
 * 内存屏障(Acquire Barrier,获取屏障):立刻获取内存中最新的值
 * ...........
 * 内存屏障(Release Barrier,释放屏障):刷新处理器的缓存,使得其他处理器看到当前线程对变量修改的结果
 * monitorexit
 *
 */
public class MyTest12_volatile {

    public static void main(String[] args) {

    }

}
