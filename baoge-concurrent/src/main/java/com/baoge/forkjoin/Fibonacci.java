package com.baoge.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author shaoxubao
 * @Date 2020/5/11 11:03
 * <p>
 * 斐波那契数列：1、1、2、3、5、8、13、21、34、…… 公式 ：F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)（n>=3，n∈N*）
 */
public class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        System.out.println("==========n:" + n);

        /**
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);

        int r = f2.compute() + f1.join();
        System.out.println("compute + join:" + r);
        return r; **/

        Fibonacci f1 = new Fibonacci(n - 1);
        Fibonacci f2 = new Fibonacci(n - 2);
        f1.fork();
        f2.fork();

        // 等待子任务执行完，得到其结果
        int leftSum = f1.join();
        int rightSum = f2.join();

        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4); // 最大并发数4
        Fibonacci fibonacci = new Fibonacci(10);
        long startTime = System.currentTimeMillis();
        Integer result = forkJoinPool.invoke(fibonacci);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

}
