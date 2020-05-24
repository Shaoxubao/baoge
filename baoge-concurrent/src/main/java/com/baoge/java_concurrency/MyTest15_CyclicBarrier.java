package com.baoge.java_concurrency;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/24
 *
 * CyclicBarrier:
 *
 *
 */
public class MyTest15_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("hello world");
        });

        for (int i = 0; i < 2; i++) {
            for (int n = 0; n < 3; n++) {
                new Thread(() -> {
                    try {
                        Thread.sleep((long) Math.random() * 2000);

                        int intRandom = new Random().nextInt(500);
                        System.out.println("hello " + intRandom);

                        cyclicBarrier.await();

                        System.out.println("world " + intRandom);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            try {
                System.out.println("阶段" + i + "运行.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
