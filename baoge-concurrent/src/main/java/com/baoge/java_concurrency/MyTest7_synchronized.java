package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/5
 *
 * 锁粗化：
 * 下面代码字节码看出每一个synchronized块都对应一个monitorenter和两个monitorexit，
 * 其实JIT编译器在执行动态编译时会对上面代码进行优化：若发现前后相邻的synchronized块使用的是同一个锁对象，
 * 那么它就会把这几个synchronized块给合并为一个较大的同步块，这样做的好处在于线程在执行这些代码时，就无需频繁申请与释放锁了，
 * 从而达到申请与释放锁一次，就可以执行完全部的同步代码块，从而提升了性能。
 *
 */
public class MyTest7_synchronized {

    Object object = new Object();

    public void method() {

//        Object object = new Object(); // object放在此处，锁消除

        synchronized (object) {
            System.out.println("hello world");
        }

        synchronized (object) {
            System.out.println("welcome");
        }

        synchronized (object) {
            System.out.println("person");
        }

    }

}
