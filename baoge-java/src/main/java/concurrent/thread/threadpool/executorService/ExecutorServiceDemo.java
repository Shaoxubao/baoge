package concurrent.thread.threadpool.executorService;

import java.util.concurrent.*;

/**
 * @Author shaoxubao
 * @Date 2020/4/14 10:07
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = new ThreadPoolExecutor(3, 3, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        CallableHandle task1 = new CallableHandle(1);
        CallableHandle task2 = new CallableHandle(2);
        CallableHandle task3 = new CallableHandle(3);
        CallableHandle task4 = new CallableHandle(4);

        Future<Integer> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);
        Future<Integer> future3 = executor.submit(task3);
        Future<Integer> future4 = executor.submit(task4);

        try {
            System.out.println("线程池活跃线程数：" + ((ThreadPoolExecutor) executor).getActiveCount());

            Integer r1 = future1.get();
            Integer r2 = future2.get();
            Integer r3 = future3.get();
            Integer r4 = future4.get();

            System.out.println("result :" + r1 + r2 + r3 + r4);

            System.out.println("线程池线程总数：" + ((ThreadPoolExecutor) executor).getPoolSize());
            System.out.println("线程池核心线程数：" + ((ThreadPoolExecutor) executor).getCorePoolSize());
            System.out.println("线程池最大线程数：" + ((ThreadPoolExecutor) executor).getMaximumPoolSize());
        } catch (Exception e) {
            e.printStackTrace();
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

            if (1 == this.taskId) {
                return 1;
            } else if (2== this.taskId) {
                return 2;
            } else if (3== this.taskId) {
                return 3;
            } else if (4== this.taskId) {
                return 4;
            }

            return 0;
        }
    }

}
