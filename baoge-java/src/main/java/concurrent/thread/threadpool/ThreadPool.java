package concurrent.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static void main(String[] args) {
        // 核心线程数量为 2，最大线程数量 4，空闲线程存活的时间 60s，有界队列长度为 3,
        //ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));

        // 核心线程数量为 2，最大线程数量 4，空闲线程存活的时间 60s， 无界队列,
        //ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        // 核心线程数量为 2，最大线程数量 4，空闲线程存活的时间 60s，有界队列长度为 3, 使用自定义拒绝策略
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new RejectedThreadPoolHandler());

        for (int i = 1; i <= 10; i++) {
            // 创建 10 个任务
            MyTask task = new MyTask(i, "任务" + i);
            // 运行
            pool.execute(task);
            System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());
        }

        // 关闭线程池
        pool.shutdown();
    }
}
