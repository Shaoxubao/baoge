package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/5
 */
public class MyThreadTest {

    public static void main(String[] args) {
        Runnable r = new MyThread();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start(); // 可能会输出两次result: 0
    }

}

class MyThread implements Runnable {

    int x = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("result: " + x++);

            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == 30) {
                break;
            }
        }
    }
}
