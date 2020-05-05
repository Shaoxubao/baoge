package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/5
 *
 * 对于synchronized关键字修饰方法来说，并没有出现monitorenter和monitorexit指令，
 * 而是出现了一个ACC_SYNCHRONIZED标志。原因是JVM使用了ACC_SYNCHRONIZED访问标志来区分一个方法是否为同步方法；
 * 当方法被调用时，调用指令会检查该方法是否拥有ACC_SYNCHRONIZED标志，
 * 如果有，那么执行线程将会先持有方法所在对象的Monitor对象，然后再去执行方法体；在该方法执行期间，
 * 其他任何线程均无法再获取到这个Monitor对象，当线程执行完该方法后，它会释放掉这个Monitor对象。
 */
public class MyTest4_synchronized {

    public synchronized void method() {
        System.out.println("hello world");
    }

}
