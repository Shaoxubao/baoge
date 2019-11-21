package com.baoge.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shaoxubao
 * @Date 2019/11/21 17:35
 *
 * 多线程控制
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public void run() {

        try {
            // 模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);

            System.out.println(Thread.currentThread().getName() + " check complete.");

            end.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(demo);
        }

        // 等待检查
        end.await();

        System.out.println("Fire!");

        executorService.shutdown();
    }

}
