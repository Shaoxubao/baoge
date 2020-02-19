package com.baoge.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Author shaoxubao
 * @Date 2020/2/19 17:40
 *
 * n个线程循环交替打印
 */
public class Demo001 {

    static int result = 0; // 打印的变量

    public static void main(String[] args) throws Exception {
        // 多个线程循环顺序打印0-100
        int n = 5;     // 线程数量
        int max = 100; // 最大值
        Thread[] threads = new Thread[n];          // 线程数组
        Semaphore[] semaphores = new Semaphore[n]; // 信号量数组
        for (int i = 0; i < n; i++) {
            semaphores[i] = new Semaphore(1); // 创建信号量，设定只有一个许可
            if (i != n - 1) {
                System.out.println("i = " + i + ", n - i = " + (n - 1));
                // 获取指定下标信号量的许可、未获取到信号量的许可的话会让线程阻塞（相对于让下面的线程调用目标信号量获取许可的时候让当前线程阻塞(除了最后一个信号量)）
                semaphores[i].acquire();
            }
        }
        for (int i = 0; i < n; i++) { // 循环创建线程并启动
            final int index = i;      // 当前线程下标
            final Semaphore lastSemaphore = i == 0 ? semaphores[n - 1] : semaphores[i - 1]; // 获取相对于当前下标的上一个信号量，如果是第一个则获取最后一个
            final Semaphore curSemaphore = semaphores[i];                                   // 当前信号量
            threads[i] = new Thread(() -> {
                try {
                    while (true) { // 循环打印
                        lastSemaphore.acquire(); // 获取相对于当前下标的上一个信号量的许可（上面除了最后一个都已经被获取了，所以肯定会是下标为0的线程最先开始获取到许可）
                        System.out.println("thread" + (index) + ":" + (result++)); // 打印数字，并自增1
                        if (result > max){ // 如果结果已经大于100，系统退出
                            System.exit(0);
                        }
                        curSemaphore.release(); // 释放当前下标对应的信号量的许可、供下一个线程使用
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            threads[i].start(); // 启动线程
        }
    }

}
