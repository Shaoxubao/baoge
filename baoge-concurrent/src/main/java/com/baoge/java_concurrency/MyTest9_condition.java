package com.baoge.java_concurrency;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/10
 *
 * Condition 实现生产者-消费者
 */
public class MyTest9_condition {

    public static void main(String[] args) {
        BoundedContainer container = new BoundedContainer();

        // 利用Java 8的流产生多个生产者线
        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            container.put("hello");
        }).start());

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            container.take();
        }).start());

    }

}

class BoundedContainer {
    private String[] elements = new String[10];
    private int elementCount; // 记录elements元素数量
    private int putIndex;
    private int takeIndex;

    private Lock lock = new ReentrantLock();

    private Condition notEmptyCondition = lock.newCondition();
    private Condition notFullCondition = lock.newCondition();

    public void put(String element) {
        this.lock.lock();
        try {
            while (this.elementCount == this.elements.length) {
                notFullCondition.await();
            }

            elements[putIndex] = element;
            if (++putIndex == this.elements.length) {
                putIndex = 0;
            }
            ++elementCount;

            System.out.println("put method:" + Arrays.toString(elements));

            // 给等待的消费者线程一个非空的条件信号
            notEmptyCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }
    }

    public String take() {
        this.lock.lock();
        try {
            while (0 == this.elements.length) {
                notEmptyCondition.await();
            }

            String element = elements[takeIndex];
            elements[takeIndex] = null;
            if (++takeIndex == elements.length) {
                takeIndex = 0;
            }
            --elementCount;

            System.out.println("take method:" + Arrays.toString(elements));

            notFullCondition.signal();

            return element;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.lock.unlock();
        }

        return null;
    }
}
