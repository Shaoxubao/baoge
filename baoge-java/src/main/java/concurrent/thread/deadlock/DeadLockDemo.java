package concurrent.thread.deadlock;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/2/16
 * Desc:   写一段死锁代码
 * 线程死锁是指由于两个或者多个线程互相持有对方所需要的资源,导致这些线程处于等待状态,无法前往执行。
 * 线程Thread1率先占有了lock1, 继续运行时需要lock2, 但此时lock2却被线程Thread2占有了，
 * 因此只能等待Thread2释放lock2才能够继续运行； 同时，Thread2也需要lock1,
 * 它只能等待Thread1释放lock1才能够继续运行， 因此，Thread1和Thread2都处于等待状态，
 * 谁也无法继续运行，即产生了死锁。
 *
 * 产生死锁还有另一种递归方式
 *
 * 三种用于避免死锁的技术：
 * 加锁顺序
 * 加锁时限
 * 死锁检测
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
