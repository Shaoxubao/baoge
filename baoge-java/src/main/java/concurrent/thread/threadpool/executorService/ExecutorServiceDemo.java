package concurrent.thread.threadpool.executorService;

import java.util.concurrent.*;

/**
 * @Author shaoxubao
 * @Date 2020/4/14 10:07
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CallableHandle task1 = new CallableHandle(1);
        CallableHandle task2 = new CallableHandle(2);
        CallableHandle task3 = new CallableHandle(3);

        Future<Integer> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);
        Future<Integer> future3 = executor.submit(task3);

        try {
            Integer r1 = future1.get();
            Integer r2 = future2.get();
            Integer r3 = future3.get();

            System.out.println(r1 + r2 + r3);
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
            if (1 == this.taskId) {
                return 1;
            } else if (2== this.taskId) {
                return 2;
            } else if (3== this.taskId) {
                return 3;
            }

            return 0;
        }
    }

}
