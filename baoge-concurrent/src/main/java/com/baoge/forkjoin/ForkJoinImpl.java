package com.baoge.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author shaoxubao
 * @Date 2020/5/11 17:13
 */
public class ForkJoinImpl extends RecursiveTask<Long> {

    // 临界值，就是结束值减开始值的结果如果小于这个值那么就不拆分了，大于这个值才会拆分
    private final int MEDIAN_NUM = 100000;
    // 从多少计算
    private int start_num = 0;
    // 计算到多少
    private int end_num = 0;

    // 构造
    public ForkJoinImpl(int start_num, int end_num) {
        this.start_num = start_num;
        this.end_num = end_num;
    }

    @Override
    protected Long compute() {
        // 结束值减开始值的结果
        int temp = end_num - start_num;
        // 判断结束值减开始值的结果是否小于上面定义的临界值
        if (temp <= MEDIAN_NUM) {
            // 如果小的话，那么就不进行拆分了，就直接调用方法开始计算
            return sequentiallySum();
        }
        // 到这就代表结束值减开始值的结果是大于临界值的
        // 继续进行拆分
        // start_num到start_num + temp / 2是把数据的左半部分形成一个新的task
        // 比如0到10，那么就是 10-0=10，temp=10，start_num=0,所以形成的新task就是(0,10/2=5),也就是左半部分
        ForkJoinImpl leftTask = new ForkJoinImpl(start_num, start_num + temp / 2);
        // 利用ForkJoinPool中的线程异步执行新创建的子任务
        leftTask.fork();

        // 这创建的就是数据的后半段，start_num + temp / 2 = 0+10/2 = 6,所以形成的新task就是(0+10/2=6,10),也就是右半部分
        ForkJoinImpl rightTask = new ForkJoinImpl(start_num + temp / 2, end_num);
        // 同时执行第二个子任务，有可能允许进一步划分
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果没有完成就等待
        Long leftResult = leftTask.join();
        // 该任务的结果是两个子任务结果的组合
        return rightResult + leftResult;
    }

    // 计算方法：在不能进行拆分的时候进行计算
    private Long sequentiallySum() {
        long sum = 0;
        for (int i = start_num; i <= end_num; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinImpl forkJoin = new ForkJoinImpl(0, 10000000);
        Long invoke = new ForkJoinPool().invoke(forkJoin);
        System.out.println("invoke = " + invoke);
    }

}
