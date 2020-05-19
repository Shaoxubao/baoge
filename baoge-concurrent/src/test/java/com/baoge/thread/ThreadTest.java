package com.baoge.thread;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author shaoxubao
 * @Date 2020/5/19 15:05
 */
public class ThreadTest {

    /**
     * 线程状态
     */
    @Test
    public void threadState() {

        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=====");
                try {
                    System.out.println("线程状态1:" + Thread.currentThread().getState());
                    Thread.sleep(2000);
//                    workQueue.take();
                    System.out.println("线程状态2:" + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("线程状态3:" + thread.getState());

        // 启动
        thread.start();

        System.out.println("线程状态4:" + thread.getState());

        // 中断
        try {
            Thread.sleep(3000);
//            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }


        while (true) {
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程状态5:" + thread.getState());

        }
    }

    @Test
    public void threadState2() {
        Thread thread = new Thread(new Task());
        print(thread.getName(), thread.getState());
        thread.start();
        // 等待线程执行完毕。
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print(thread.getName(), thread.getState());
    }

    class Task implements Runnable {
        @Override
        public void run() {
            print(Thread.currentThread().getName(), Thread.currentThread().getState());
        }
    }

    private static final String stringFormat = "%s:%s";

    private static void print(String threadName, Thread.State state) {
        System.out.println(String.format(stringFormat, threadName, state));
    }

}
