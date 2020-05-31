package com.baoge.java_concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/30
 *
 * 对于ReentrantLock来说，其执行逻辑如下：
 *
 * 1.尝试获取对象的锁，如果获取不到（意味着已经有其他线程持有了锁，并且尚未释放），那么它就会进入到AQS的阻塞队列中；
 * 2.如果获取到，根据锁是公平还是非公平进行不同处理：
 *   a.如果是公平锁，那么线程直接放置到AQS阻塞队列的末尾
 *   b.如果是非公平锁，那么线程会首先进行CAS计算，如果成功，则直接获取到锁；
 *     如果失败，则与公平锁的处理方式一致，被放到阻塞队列末尾。
 * 3.当锁被释放时，那么底层会调用release方法对state变量进行减一操作，如果减一后，state值不为0，那么release操作完毕，
 *   如果减一操作后，state值为0，则调用LockSupport的unpark方法唤醒等待队列中的第一个后继线程，将其唤醒，使其能够获取
 *   到对象的锁（release时公平锁和非公平锁的处理逻辑是一致的）；
 *   之所以调用release方法后state值可能不为0，原因在于ReentrantLock是可重入锁，表示线程可以多次调用lock方法，
 *   导致每调用一次，state值就会加1。
 *
 */
public class MyTest21_ReentrantLock {

    private Lock lock = new ReentrantLock();

    public void method() {
        try {
            lock.lock();
            Thread.sleep(2000);

            System.out.println("method invoked");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyTest21_ReentrantLock test21ReentrantLock = new MyTest21_ReentrantLock();
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
               test21ReentrantLock.method();
            }).start();
        });
    }

}
