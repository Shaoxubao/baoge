package com.baoge.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shaoxubao
 * @Date 2019/11/21 17:35
 *
 * 多线程控制：
 * CountDown表示减法计数，Latch表示门闩的意思，计数为0的时候就可以打开门闩了。Cyclic Barrier表示循环的障碍物。
 * 两个类都含有这一个意思：对应的线程都完成工作之后再进行下一步动作，也就是大家都准备好之后再进行下一步。然而两者最大的区别是，进行下一步动作的动作实施者是不一样的。
 * 这里的“动作实施者”有两种，一种是主线程（即执行main函数），另一种是执行任务的其他线程，后面叫这种线程为“其他线程”，区分于主线程。
 * 对于CountDownLatch，当计数为0的时候，下一步的动作实施者是main函数；对于CyclicBarrier，下一步动作实施者是“其他线程”。
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
