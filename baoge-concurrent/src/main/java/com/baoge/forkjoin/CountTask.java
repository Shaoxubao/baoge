package com.baoge.forkjoin;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author shaoxubao
 * @Date 2020/4/28 9:24
 * <p>
 * 使用fork join计算1+2+3+4+n的和
 */
public class CountTask extends RecursiveTask {

    private static final int THRESHOLD = 2; // 阈值
    private static AtomicInteger splitCount = new AtomicInteger(0); // 任务分裂次数
    private static AtomicInteger calCount = new AtomicInteger(0);   // 任务求和次数

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Object compute() {
        int sum = 0;

        // 如果任务小到阈值，直接计算和
        if ((end - start) <= THRESHOLD) {
            System.out.println("start=" + start + ", end=" + end);
            for (int i = start; i <= end; i++) {
                sum += i;
            }

            calCount.incrementAndGet();
        } else {
            // 如果任务大于阀值，就分裂成两个子任务计算
            int middle = (end + start) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，得到其结果
            int leftSum = (int) leftTask.join();
            int rightSum = (int) rightTask.join();

            // 合并子任务
            sum = leftSum + rightSum;

            splitCount.incrementAndGet();
        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // 生成一个计算任务，负责计算1+2+3+4+5+n
        CountTask task = new CountTask(1, 4);
        // 执行任务
        Future result = forkJoinPool.submit(task);

        try {
            System.out.println("任务执行结果：" + result.get());
            System.out.println("任务分裂次数：" + splitCount.get());
            System.out.println("任务求和次数：" + calCount.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
