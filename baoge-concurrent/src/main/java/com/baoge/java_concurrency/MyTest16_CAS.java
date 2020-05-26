package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/26
 *
 * CAS:
 * 1.synchronized关键字和Lock等锁机制都是悲观锁，无论做任何操作都要先上锁，接下来做后续操作，从而确保了
 *   接下来的所有操作都是由当前这个线程来执行的。
 * 2.乐观锁：线程操作前不会做任何预先的处理，而是直接去执行；当在最后执行变量更新的时候，当前线程需要有一种机制
 *   来确保当前被操作的变量没有被其他线程修改的，CAS是乐观锁极为重要的一种实现方式。
 *
 * CAS(Compare And Swap)
 * 比较与交换：这是一个不断循环的过程，一直到变量被修改成功为止，CAS本身是由硬件指令来提供支持的，换句话说，
 * 硬件中是通过一个原子指令来实现比较与交换的，因此，CAS可以确保变量操作的原子性。
 *
 *
 */
public class MyTest16_CAS {

    public static void main(String[] args) {

    }

}
