package com.baoge.java_concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/24
 *
 * 传统上，我们通过synchronized关键字 + wait + notify/notifyAll 来实现多个线程之间的协调与通信，整个过程是有
 * JVM来实现的；
 * 从JDK 5开始，并发包提供了Lock、Condition(await与signal/signalAll)来实现多个线程之间的协调与通信，
 * 整个过程由开发者来控制的，而且相比于传统方式，更加灵活，功能更强大
 *
 */
public class MyTest11_condition {

    public static void main(String[] args) {
        BoundedContainer2 container = new BoundedContainer2();

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                container.put("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                String result = container.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

    }

}

/**
 * 有界容器
 */
class BoundedContainer2 {

    private String[] elements = new String[10];

    private Lock lock = new ReentrantLock();
    private Condition notEmptyCondition = lock.newCondition();
    private Condition notFullCondition = lock.newCondition();

    private int elementCount; // element数组中发已有元素数量
    private int putIndex;     // 插入索引
    private int takeIndex;    // 取出索引

    public void put(String element) throws InterruptedException {
        this.lock.lock();

        try {
            while (elementCount == elements.length) {
                notFullCondition.await();
            }

            elements[putIndex] = element;

            if (++putIndex == elements.length) {
                putIndex = 0;
            }

            ++elementCount;

            System.out.println("put method: " + Arrays.toString(elements));

            notEmptyCondition.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lock();

        String result = null;
        try {
            while (elementCount == 0) {
                notEmptyCondition.await();
            }

            result = elements[takeIndex];
            elements[takeIndex] = null; // 取出元素位置置为null

            if (++takeIndex == elements.length) {
                takeIndex = 0;
            }

            --elementCount;

            System.out.println("take method: " + Arrays.toString(elements));

            notFullCondition.signal();

            return result;
        } finally {
            lock.unlock();
        }
    }

}
