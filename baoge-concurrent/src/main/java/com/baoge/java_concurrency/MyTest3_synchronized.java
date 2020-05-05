package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/5
 *
 * 编译后target目录下
 * javap MyTest3_synchronized.class 查看反编译结果
 * 查看字节码可以用以下两种方式：
 *  javap -c MyTest3_synchronized.class
 *  javap -v MyTest3_synchronized.class
 *
 *  总结：
 *  当使用synchronized关键字来修饰代码块时，字节码层面上是通过monitorenter和monitorexit指令来实现锁的获取与释放动作。
 *  当线程进入到monitorenter指令后，线程将会持有Monitor对象，退出monitorexit指令后，线程将会释放Monitor对象。
 *
 */
public class MyTest3_synchronized {

    private Object object = new Object();

    public void method() {
        synchronized (object) {
            System.out.println("hello world");

            // 加此句代码，字节码中只有一个monitorexit，因为该方法无论如何肯定都是以异常进行同步块退出的，这是唯一的可能，没有其它更多的路径，所以这就是为啥只有一个monitorexit助字符存在的原因。
            throw new RuntimeException();
        }
    }

    public void method2() {
        synchronized (object) {
            System.out.println("welcome");
        }
    }

}
