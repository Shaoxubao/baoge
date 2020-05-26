package com.baoge.java_concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 *
 * 对于CAS来说，其操作数涉及到三个：
 * 1.需要被操作的内存值V
 * 2.需要进行比较的值A
 * 3.需要进行写入的值B
 * 只有当V == A的时候，CAS才会通过原子操作的手段将V的值更新为B。
 *
 * 关于CAS限制问题：
 * 1.循环开销问题：并发量大的情况下会导致线程一直自旋；
 * 2.只能保证一个变量的原子操作；可通过AtomicReference来实现对多个变量的原子操作；
 * 3.ABA问题：1 -> 3 -> 1，可增加版本号规避这个问题
 *
 */
public class MyTest17_CAS {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.get());

        // 返回更新为8之前的值
        System.out.println(atomicInteger.getAndSet(8));

        System.out.println(atomicInteger.get());

        // 返回自增1之前的值
        System.out.println(atomicInteger.getAndIncrement());

        System.out.println(atomicInteger.get());
    }

}
