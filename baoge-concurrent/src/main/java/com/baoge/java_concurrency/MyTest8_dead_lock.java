package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/5
 *
 * 死锁：线程1等待线程2互斥持有的资源，而线程2也在等待线程1互斥持有的资源，两个线程都无法继续执行。
 *
 * 活锁(一个线程)：线程持续重试一个总是失败的操作，导致无法继续执行。
 *
 * 饿死：线程一直被调度器延迟访问其赖以执行的资源，也许是调度器先于低优先级的线程而执行高优先级的线程，
 * 同时总是会有一个高优先级的线程可以执行，饿死也叫无限延迟。
 *
 * jvm demo中MyTest3_deadlock也有实例讲解
 */
public class MyTest8_dead_lock {

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("method1 invoked");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("method2 invoked");
            }
        }
    }

    public static void main(String[] args) {
        MyTest8_dead_lock myTest8DeadLock = new MyTest8_dead_lock();

        Runnable runnable1 = () -> {
          while (true) {
              myTest8DeadLock.method1();

              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };

        Runnable runnable2 = () -> {
            while (true) {
                myTest8DeadLock.method2();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runnable1, "thread1");
        Thread thread2 = new Thread(runnable2, "thread2");

        thread1.start();
        thread2.start();
    }

}
