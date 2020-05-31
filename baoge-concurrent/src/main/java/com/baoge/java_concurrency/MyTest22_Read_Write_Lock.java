package com.baoge.java_concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/30
 *
 * 关于ReentrantReadWriteLock的执行逻辑：
 *
 * 读锁：
 * 1.在获取读锁时，会尝试判断当前对象是否拥有了写锁，如果已经拥有，则直接失败；
 * 2.如果么有写锁，则表示当前对象没有排他锁，则当前线程会尝试给对象加锁；
 * 3.如果当前线程已经持有了该对象的锁，那么直接将读锁数量加1；
 *
 * 写锁(排他锁)：
 * 1.在获取写锁时，会尝试判断当前对象是否拥有了(读锁与写锁)，如果已经拥有并且持有的线程并非当前线程，直接失败；
 * 2.如果当前对象没有被加锁，那么写锁就会为当前对象上锁，并且将写锁的数量加1；
 * 3.将当前对象的排他锁线程持有者设置为自己。
 */
public class MyTest22_Read_Write_Lock {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void method() {
        try {
            readWriteLock.readLock().lock();

            Thread.sleep(1000);

            System.out.println("method invoked");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        MyTest22_Read_Write_Lock readWriteLock = new MyTest22_Read_Write_Lock();
        IntStream.range(0, 10).forEach(i -> new Thread(readWriteLock::method).start());

    }

}
