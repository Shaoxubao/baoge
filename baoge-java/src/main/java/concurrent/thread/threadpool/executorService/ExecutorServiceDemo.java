package concurrent.thread.threadpool.executorService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author shaoxubao
 * @Date 2020/4/14 10:07
 *
 * JDK 提供了四种内置拒绝策略：
 * 1、DiscardPolicy: 默默丢弃无法处理的任务，不予任何处理
 * 2、DiscardOldestPolicy: 丢弃队列中最老的任务, 尝试再次提交当前任务
 * 3、AbortPolicy: 直接抛异常，阻止系统正常工作。
 * 4、CallerRunsPolicy: 将任务分给调用线程来执行,运行当前被丢弃的任务，这样做不会真的丢弃任务，但是提交的线程性能有可能急剧下降。
 */
public class ExecutorServiceDemo {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        ExecutorService executor = new ThreadPoolExecutor(1, 2, 0L,
//                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(1, 2, 0L,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy()); // 有界队列
        CallableHandle task1 = new CallableHandle(1);
        CallableHandle task2 = new CallableHandle(2);
        CallableHandle task3 = new CallableHandle(3);
        CallableHandle task4 = new CallableHandle(4);
        CallableHandle task5 = new CallableHandle(5);

        try {
            // 打印线程池状态
            printThreadPoolState(executor);

            Future<Integer> future1 = executor.submit(task1);
            Future<Integer> future2 = executor.submit(task2);
            Future<Integer> future3 = executor.submit(task3);
            Future<Integer> future4 = executor.submit(task4);
            Future<Integer> future5 = executor.submit(task5);

            // 打印线程池状态
            printThreadPoolState(executor);

            Integer r1 = future1.get();

            // 打印线程池状态
            printThreadPoolState(executor);

            Integer r2 = future2.get();

            // 打印线程池状态
            printThreadPoolState(executor);

            Integer r3 = future3.get();

            // 打印线程池状态
            printThreadPoolState(executor);

            Integer r4 = future4.get();

            // 打印线程池状态
            printThreadPoolState(executor);

            Integer r5 = future5.get();

            System.out.println("============result :" + (r1 + r2 + r3 + r4 + r5));
            // 打印线程池状态
            printThreadPoolState(executor);
        } catch (Exception e) {
            e.printStackTrace();
            // 打印线程池状态
            printThreadPoolState(executor);
        } finally {
            executor.shutdown(); // 关闭线程池
        }
    }

    static class CallableHandle implements Callable<Integer> {

        private final int taskId;

        // 构造器
        public CallableHandle(int taskId) {
            this.taskId = taskId;
        }

        // 实现Callable接口的call方法,会在线程池submit(this)的时候调用本方法
        public Integer call() {
            System.out.println("当前线程：" + Thread.currentThread().getName());

            try {
                if (1 == this.taskId) {
                    TimeUnit.SECONDS.sleep(1);
                    return 1;
                } else if (2== this.taskId) {
                    TimeUnit.SECONDS.sleep(1);
                    return 2;
                } else if (3== this.taskId) {
                    TimeUnit.SECONDS.sleep(1);
                    return 3;
                } else if (4== this.taskId) {
                    TimeUnit.SECONDS.sleep(1);
                    return 4;
                } else if (5== this.taskId) {
                    TimeUnit.SECONDS.sleep(1);
                    return 5;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }
    }

    public static void printThreadPoolState(ExecutorService executor) {
        System.out.println("=========================:" + count.incrementAndGet());
        System.out.println("线程池正在执行任务线程数：" + ((ThreadPoolExecutor) executor).getActiveCount());
        System.out.println("线程池线程总数：" + ((ThreadPoolExecutor) executor).getPoolSize());
        System.out.println("线程池核心线程数：" + ((ThreadPoolExecutor) executor).getCorePoolSize());
        System.out.println("最大允许的线程数：" + ((ThreadPoolExecutor) executor).getMaximumPoolSize());
        System.out.println("线程空闲时间：" + ((ThreadPoolExecutor) executor).getKeepAliveTime(TimeUnit.MILLISECONDS));
        System.out.println("任务总数：" + ((ThreadPoolExecutor) executor).getTaskCount());
        System.out.println("已完成任务数量：" + ((ThreadPoolExecutor) executor).getCompletedTaskCount());
        System.out.println("队列里缓存的任务数量：" + ((ThreadPoolExecutor) executor).getQueue().size());
        System.out.println("池中存在的最大线程数：" + ((ThreadPoolExecutor) executor).getLargestPoolSize());
    }

}
