package com.baoge.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author shaoxubao
 * @Date 2020/5/19 15:05
 */
public class ThreadTest {

    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();

    /**
     * 线程状态
     */
    @Test
    public void threadState() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=====");
                try {
                    System.out.println("线程状态1:" + Thread.currentThread().getState());
                    Thread.sleep(2000);
//                    workQueue.take();
                    System.out.println("线程状态2:" + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("线程状态3:" + thread.getState());

        // 启动
        thread.start();

        System.out.println("线程状态4:" + thread.getState());

        // 中断
        try {
            Thread.sleep(3000);
//            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }


        while (true) {
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程状态5:" + thread.getState());

        }
    }

    /**
     * 线程状态：
     * RUNNABLE——>WAITING——>InterruptedException——>WAITING
     */
    @Test
    public void testQueue() {
        Thread thread = new Thread(new Task1());

        thread.start();
        System.out.println(thread.getName() + "-" + thread.getState());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    thread.interrupt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            try {
                System.out.println(thread.getName() + "-" + thread.getState());
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Task1 implements Runnable {
        @Override
        public void run() {
            System.out.println("run......");
            runWorker(this);
        }
    }

    private void runWorker(Task1 task1) {
        Runnable task = null;
        while ((task = getTask()) != null) {
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    private Runnable getTask() {
        for (; ; ) {
            try {
                System.out.println("workQueue size:" + workQueue.size());
                Runnable r = !workQueue.isEmpty() ? workQueue.poll() : workQueue.take();
                System.out.println("workQueue ------------");
                if (r != null) {
                    return r;
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException.......");
                e.printStackTrace();
            }
        }
    }

    @Test
    public void threadState2() {
        Thread thread = new Thread(new Task());
        print(thread.getName(), thread.getState());
        thread.start();
        // 等待线程执行完毕。
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print(thread.getName(), thread.getState());
    }

    class Task implements Runnable {
        @Override
        public void run() {
            print(Thread.currentThread().getName(), Thread.currentThread().getState());
        }
    }

    private static final String stringFormat = "%s:%s";

    private static void print(String threadName, Thread.State state) {
        System.out.println(String.format(stringFormat, threadName, state));
    }

    @Test
    public void testThreadPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        try {
            for (int i = 1; i <= 1; i++) {
                // 创建 10 个任务
                MyTask task = new MyTask(i, "任务" + i);
                // 运行
                pool.execute(task);
                System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }


        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class MyTask implements Runnable {

        private int taskId;          // 任务 id
        private String taskName;     // 任务名字

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public MyTask(int taskId, String taskName) {
            this.taskId = taskId;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("当前正在执行 ******   线程Id-->" + taskId + ",任务名称-->" + taskName);
            try {
                Thread.currentThread().sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程Id-->" + taskId + ",任务名称-->" + taskName + "   -----------   执行完毕！");
        }

    }

}
