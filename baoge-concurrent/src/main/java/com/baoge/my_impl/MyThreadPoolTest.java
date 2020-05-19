package com.baoge.my_impl;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/5/18 16:08
 */
public class MyThreadPoolTest {

    public static void main(String[] args) throws Exception {
        MyThreadPool myThreadPool = null;
        try {
            myThreadPool = new MyThreadPool(1, new LinkedBlockingDeque<>());
            for (int i = 0; i < 1; i++) {
                MyTask task = new MyTask(i);
                myThreadPool.execute(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myThreadPool.shutdown();
        }
//        printThreadState(myThreadPool);
    }

    private static class MyTask implements Runnable {

        private int taskNum;

        public MyTask(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);

                System.out.println("当前运行线程:" + Thread.currentThread().getName() + "执行任务:" + taskNum);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printThreadState(MyThreadPool myThreadPool) throws Exception {
        while (true) {
            System.out.println("监控到线程状态-----------------------");
            HashSet<MyThreadPool.WorkerThread> workers = myThreadPool.getWorkers();
            for (MyThreadPool.WorkerThread item : workers) {
                System.out.println("监控到线程状态：" + item.thread.getName() + ":" + item.thread.getState());
            }

            System.out.println("监控到线程状态-----------------------");
            Thread.sleep(2000);
        }
    }

}
