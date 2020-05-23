package com.baoge.java_concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/23
 *
 *  关于Lock和synchronized区别：
 *  1.锁的获取方式：前者通过代码手工获取，后者通过VJM获取
 *  2.具体实现方式：前者通过Java代码，后者是JVM底层
 *  3.锁的释放方式：前者通过通过unlock方法在finally块中释放，后者JVM来释放
 *  4.锁的具体类型：前者提供如公平锁、非公平锁等，两者都提供了可重入锁
 */
public class MyTest10_reentrantlock {

    private Lock lock = new ReentrantLock();

    public void method1() {
        try {
            lock.lock();

            System.out.println("method1 invoked.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
//        try {
//            lock.lock();
//
//            System.out.println("method2 invoked.");
//        } finally {
//            lock.unlock();
//        }

        boolean result = false;

        try {
            result = lock.tryLock(800, TimeUnit.MILLISECONDS);
            if (result) {
                System.out.println("get the lock");
            } else {
                System.out.println("not get the lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (result) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MyTest10_reentrantlock test = new MyTest10_reentrantlock();

        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 5; i++) {
               test.method1();
//               try {
//                   Thread.sleep(2000);
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }
           }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                test.method2();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        t1.start();
        t2.start();

    }

}
