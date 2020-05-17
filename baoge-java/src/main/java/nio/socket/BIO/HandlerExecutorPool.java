package nio.socket.BIO;

import java.util.concurrent.*;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/10/26
 */
public class HandlerExecutorPool {
    /**
     * 自定义线程池方法签名：
     * public ThreadPoolExecutor(int corePoolSize,   // 核心线程数--线程池初始化创建的线程数量
     *  int maximumPoolSize,                         // 最大线程数，线程池中能创建的最大线程数
     *  long keepAliveTime,                          // 线程存活时间
     *  TimeUnit unit,                               // 线程存货时间单位
     *  BlockingQueue<Runnable> workQueue,           // 一个阻塞队列
     *  ThreadFactory threadFactory                  // 拒绝策略
     *) {……}
     *
     * 使用有界队列
     *
     *若有新的任务需要执行，如果线程池实际线程数小于corePoolSize核心线程数的时候，则优先创建线程。
     * 若大于corePoolSize时，则会将多余的线程存放在队列中，
     * 若队列已满，且请求线程小于maximumPoolSize的情况下，
     * 则自定义的线程池会创建新的线程，
     * 若队列已满，且请求线程大于maximumPoolSize的情况下，则执行拒绝策略，或其他自定义方式。
     *
     * 使用无界队列
     *
     *LinkedBlockingQueue与有界队列相比，除非系统资源耗尽，否则无界队列不存在任务入队失败的情况，若系统的线
     *程数小于corePoolSize时，则新建线程执行corePoolSize，当达到corePoolSize后，
     * 则把多余的任务放入队列中等待执行若任务的创建和处理的速速差异很大，无界队列会保持快速增长，
     * 直到耗尽系统内存为之，对于无界队列的线程池maximumPoolSize并无真实用处。
     *
     *
     * jdk给我们提供了一些拒绝策略
     *
     *1.AbortPolicy：直接抛出异常，系统正常工作。（默认的策略）
     *2.CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中执行，运行当前被丢弃的任务。
     *3.DiscardOrderstPolicy：丢弃最老的请求，尝试再次提交当前任务。
     *4.丢弃无法处理的任务，不给于任何处理。
     *如果需要自定义策略，需要实现RejectedExecutionHandler接口。
     *
     *
     *
     */

    private ThreadPoolExecutor threadPool;

    public HandlerExecutorPool(int corePoolSize,
                               int maximumPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               BlockingQueue<Runnable> workQueue) {
        this.threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit.SECONDS,
                workQueue);
    }

    public void execute(Runnable task) {
        this.threadPool.execute(task);
    }

    public int getActiveCount () {
        return threadPool.getActiveCount();
    }

}
