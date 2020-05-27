package com.baoge.java_concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/27
 */
public class MyTest18_Callable {

    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println("pre execution");

            Thread.sleep(5000);

            int randomInt = new Random().nextInt(600);

            System.out.println("post execution");

            return randomInt;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();

        System.out.println("thread has started");

        try {
            Thread.sleep(2000);
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
