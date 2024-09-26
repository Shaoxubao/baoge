package algorithm.huadongwindow;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class Demo1 {
    // 为滑动窗口准备一个队列存放元素的集合
    Queue<Integer> queue = new LinkedList<>();
    private volatile boolean flag = false;

    ThreadPoolExecutor executor = new MyThreadPool(10, 20, 6000, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            MyTask task = (MyTask) r;
            System.out.println("存入db或者发送到mq等待重试，此时retryEpoch=0");
            // 滑动时间窗口判断是否需要进行告警，以方便人工干预
            int windowSize = 300; // 设置时间窗口大小为300
            long currentTimeMillis = System.currentTimeMillis();
            // 如果队列不为空且最早入队的元素已经超过了时间窗口规定大小（单位为s）
            // 则进行告警，人工干涉，并移除最老的元素
            if (!queue.isEmpty() && currentTimeMillis - queue.peek() > windowSize * 1000) {
                queue.poll();
            }
            // 将新元素加入队列队尾
            queue.offer((int) System.currentTimeMillis());
        }
    });

    public static void main(String[] args) {
        Demo1 obj = new Demo1();
        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask(i, 0);
            obj.executor.execute(myTask);
            System.out.println(">>>>>>" + i);
        }
        System.out.println(">>>>> end");
    }

    class MyThreadPool extends ThreadPoolExecutor {
        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            long taskCount = this.getTaskCount();
            int activeCount = this.getActiveCount();
            long completedTaskCount = this.getCompletedTaskCount();
            // 等等指标，将这些指标发送到mq，消费这些指标生成监控图
            String ret = String.format("taskCount=%d, activeCount=%d, completedTaskCount=%d", taskCount, activeCount, completedTaskCount);
            System.out.println(ret);
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (!queue.isEmpty() && this.getQueue().size() < 1000) {
                // 重试被拒绝的任务
                System.out.println("从db获取mq获取被拒绝的任务");
                System.out.println("判断任务的重试次数，如果超过某个阈值，则进行告警");
                System.out.println("将任务重新添加到线程池，executor.execute(task)，并将retryEpoch+1");
            }
            if (!flag && !executor.isShutdown()) {
                executor.execute(new MyTask("retry", 1));
                flag = true;
            }
        }
    }

    // 创建任务类
    static class MyTask implements Runnable {
        private Object data; // 任务数据
        private Integer retryEpoch; // 重试纪元


        public MyTask(Object data, Integer retryEpoch) {
            this.data = data;
            this.retryEpoch = retryEpoch;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Integer getRetryEpoch() {
            return retryEpoch;
        }

        public void setRetryEpoch(Integer retryEpoch) {
            this.retryEpoch = retryEpoch;
        }

        @Override
        public void run() {
            // 任务处理
            System.out.println("处理任务，data=" + data.toString());
        }
    }
}

