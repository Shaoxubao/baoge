package com.baoge.forkjoin;

import com.baoge.utils.Utils;

import java.util.concurrent.*;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/28
 *
 * 线程池计算数组之和
 */
public class ThreadPoolForCount {

    public static class RecursiveSumTask implements Callable<Long> {
        public static final int SEQUENTIAL_CUTOFF = 1;
        int lo;
        int hi;
        int[] arr;
        ExecutorService executorService;

        public RecursiveSumTask(ExecutorService executorService, int[] arr, int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            this.arr = arr;
            this.executorService = executorService;
        }

        @Override
        public Long call() throws Exception {
//            System.out.format("%s range [%d - %d] begin to compute %n", Thread.currentThread().getName(), lo, hi);

            long result = 0;
            if ((hi - lo) <= SEQUENTIAL_CUTOFF) {
                for (int i = lo; i < hi; i++) {
                    result += arr[i];
                }
                System.out.format("%s range [%d - %d] begin to finished %n", Thread.currentThread().getName(), lo, hi);
            } else {
                int middle = (lo + hi) / 2;

                RecursiveSumTask left = new RecursiveSumTask( executorService, arr, lo, middle);
                RecursiveSumTask right = new RecursiveSumTask(executorService, arr, middle, hi);

                Future<Long> lr = executorService.submit(left);
                Future<Long> rr = executorService.submit(right);

                result = lr.get() + rr.get();
//                System.out.format("%s range [%d - %d] begin to finished to compute %n", Thread.currentThread().getName(), lo, hi);
            }

            return result;
        }
    }

    public static long sum(int[] arr) {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        ExecutorService executorService = Executors.newCachedThreadPool();

        RecursiveSumTask task = new RecursiveSumTask(executorService, arr, 0, arr.length);
        Future<Long> future = executorService.submit(task);

        long result = 0;
        try {
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] arr = Utils.buildRandomIntArray(10);

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.printf("the arr length is: %d\n", arr.length);

        long result = sum(arr);

        System.out.println("the result is: " + result);
    }

}
