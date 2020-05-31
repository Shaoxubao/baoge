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
 *
 *
 * 关于synchronized关键字和AQS之间的关系：
 *
 * 1.synchronized关键字底层的C++实现中，存在两个重要的数据结构(集合)：WaitSet和EntryList
 * 2.WaitSet存放的是调用了Object的await方法的线程对象(被封装成C++的Node对象)
 * 3.EntrySet中存放的是陷入到阻塞状态、需要获取monitor的那些线程对象
 * 4.当一个线程被notify后，它就会从WaitSet中移动到EntryList中
 * 5.进入到EntryList后，该线程依然要与其他线程争抢monitor对象
 * 6.如果争抢到，就表示该线程获取了对象锁，它就可以以排他方式执行对应同步块代码。
 *
 * 1.AQS中存在两种队列，分别是Condition对象上的条件队列，以及AQS本身的阻塞队列，这两个队列中的每一个
 *   对象都是Node实例(里面封装了线程对象)，当位于Condition条件队列中的线程被其他线程signal后，该线程
 *   就会从条件队列中移动到AQS的阻塞队列中。
 * 2.位于AQS阻塞队列中的Node对象本质上都是由双向链表构成的。
 * 3.在获取AQS锁时，这些进入到阻塞队列中的线程会按照在队列中的排序先后尝试获取。
 * 4.当AQS阻塞队列中线程获取到锁后，就表示该线程已经可以正常执行了。
 * 5.陷入到阻塞状态的线程，依然需要进入到操作系统的内核态，进入阻塞(park方法实现)。
 *
 *
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
