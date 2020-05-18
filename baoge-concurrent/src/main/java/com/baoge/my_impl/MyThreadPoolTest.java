package com.baoge.my_impl;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/5/18 16:08
 */
public class MyThreadPoolTest {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = null;
        try {
            myThreadPool = new MyThreadPool(4, new LinkedBlockingDeque<>());
            for (int i = 0; i < 10; i++) {
                MyTask task = new MyTask();
                myThreadPool.execute(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            myThreadPool.shutdown();
        }
    }

    private static class MyTask implements Runnable {
        @Override
        public void run() {
            try {
                if (!Thread.currentThread().isInterrupted()) {
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("当前运行线程:" + Thread.currentThread().getName());
        }
    }

}
