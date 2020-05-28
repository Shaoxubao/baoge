package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/28
 *
 * ThreadLocal
 * 本质上，ThreadLocal是通过空间来换取时间，从而实现每个线程当中都会有一个变量的副本，这样每个线程都会操作
 * 该副本，从而完全规避了多线程的并发问题。
 *
 * Java中存在四种类型的引用：
 * 1.强引用(strong)
 * 2.软引用(soft)
 * 3.弱引用(weak)
 * 4.虚引用(phantom)
 *
 * public class Test {
 *     private static final ThreadLocal<String> t1 = new ThreadLocal();
 * }
 *
 * try {
 *     ...
 *     ...
 * } finally {
 *     t1.remove();
 * }
 *
 *
 */
public class MyTest20_ThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello world");
        System.out.println(threadLocal.get());
        threadLocal.set("welcome");
        System.out.println(threadLocal.get());

    }

}
