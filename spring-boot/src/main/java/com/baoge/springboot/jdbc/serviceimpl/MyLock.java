package com.baoge.springboot.jdbc.serviceimpl;

import com.baoge.springboot.untils.UnsafeInstance;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/1
 */
public class MyLock {

    private volatile int state = 0;
    private final static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    private final static long stateOffset;
    private Thread lockHolder;
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue();

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(BookStockServiceImpl.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean compareAndSwapState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    public void lock(int state) {
        Thread currentThread = Thread.currentThread();

        for ( ; ; ) {
            if (state == 0) {
                if (compareAndSwapState(0, 1)) {
                    lockHolder = currentThread; // 加锁成功
                    break;
                }
            }

            queue.add(currentThread);
            LockSupport.park(); // 阻塞未获取锁的线程
        }
    }

    public void unlock(int state, Thread currentThread) {
        for ( ; ; ) {
            if (state != 0 && lockHolder == currentThread) {
                // 释放当前线程
                compareAndSwapState(state, 0);

                // 唤醒被阻塞线程
                if (queue.size() > 0) {
                    Thread thread = queue.poll();
                    LockSupport.unpark(thread);
                }

                break;
            }

        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
