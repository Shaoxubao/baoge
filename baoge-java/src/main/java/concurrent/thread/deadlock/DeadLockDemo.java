package concurrent.thread.deadlock;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/2/16
 * Desc:   写一段死锁代码
 */
public class DeadLockDemo {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "锁住lock1");
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + "锁住lock2");
                    }
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "锁住lock2");
                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getName() + "锁住lock1");
                    }
                }
            }
        }, "t2");


        t1.start();
        t2.start();

    }

}
