package com.baoge.jvm.memory;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/8
 *
 * 线程死锁（jconsole观察）
 * VM配置：  -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
 * 再启动jconsole远程连接localhost:8099即可
 * 也可通过jvisualvm观察：
 * Found one Java-level deadlock:
 * =============================
 * "Thread-B":
 *   waiting to lock monitor 0x0000000017fa3168 (object 0x00000000d6941098, a java.lang.Class),
 *   which is held by "Thread-A"
 * "Thread-A":
 *   waiting to lock monitor 0x0000000017fa3588 (object 0x00000000d69e7380, a java.lang.Class),
 *   which is held by "Thread-B"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread-B":
 *         at com.baoge.jvm.memory.A.method(MyTest3.java:31)
 *         - waiting to lock <0x00000000d6941098> (a java.lang.Class for com.baoge.jvm.memory.A)
 *         at com.baoge.jvm.memory.B.method(MyTest3.java:46)
 *         - locked <0x00000000d69e7380> (a java.lang.Class for com.baoge.jvm.memory.B)
 *         at com.baoge.jvm.memory.MyTest3.lambda$main$1(MyTest3.java:17)
 *         at com.baoge.jvm.memory.MyTest3$$Lambda$6/2027961269.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:748)
 * "Thread-A":
 *         at com.baoge.jvm.memory.B.method(MyTest3.java:43)
 *         - waiting to lock <0x00000000d69e7380> (a java.lang.Class for com.baoge.jvm.memory.B)
 *         at com.baoge.jvm.memory.A.method(MyTest3.java:34)
 *         - locked <0x00000000d6941098> (a java.lang.Class for com.baoge.jvm.memory.A)
 *         at com.baoge.jvm.memory.MyTest3.lambda$main$0(MyTest3.java:16)
 *         at com.baoge.jvm.memory.MyTest3$$Lambda$5/580024961.run(Unknown Source)
 *         at java.lang.Thread.run(Thread.java:748)
 */
public class MyTest3 {

    public static void main(String[] args) {

        new Thread(() -> A.method(), "Thread-A").start();
        new Thread(() -> B.method(), "Thread-B").start();

        try {
            Thread.sleep(50000); // 加此睡眠，可在jconsole中看到main线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class A {
    public static synchronized void method() {
        System.out.println("method from A");
        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B {
    public static synchronized void method() {
        System.out.println("method from B");
        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
