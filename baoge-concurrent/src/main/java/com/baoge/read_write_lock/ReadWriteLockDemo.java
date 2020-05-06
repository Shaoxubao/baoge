package com.baoge.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author shaoxubao
 * @Date 2020/5/6 10:00
 * <p>
 * 使用jdk提供的读写锁控制模拟并发读写HashMap
 */
public class ReadWriteLockDemo {

    private static final int COUNT = 200000;
    //    private static CountDownLatch countDownLatch = new CountDownLatch(COUNT * 2); // 读写并发
    private static CountDownLatch countDownLatch = new CountDownLatch(COUNT);           // 写并发

    public static void main(String[] args) {

        // 固定线程个数线程池
        ExecutorService executorService = Executors.newFixedThreadPool(7);

        try {
            long startTime = System.currentTimeMillis();

//            MyData myData = new MyData();
            MyDataSafety myData = new MyDataSafety();
            for (int i = 0; i < COUNT; i++) {

                // 写数据
                MyDataWriteTask writeTask = new MyDataWriteTask(myData, i, countDownLatch);
                // 读数据
                MyDataReadTask readTask = new MyDataReadTask(myData, countDownLatch);

                executorService.execute(writeTask);
//                executorService.execute(readTask);
            }
            countDownLatch.await();

            System.out.println("result: " + myData.getData().size()); // 以MyData为例，count设置足够大时如2000000，输出会小于count
            System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}

/**
 * 读task
 */
class MyDataReadTask implements Runnable {

    private Object myData;

    private CountDownLatch countDownLatch;

    public MyDataReadTask(Object myData, CountDownLatch countDownLatch) {
        this.myData = myData;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if (myData instanceof MyData) {
            MyData newMyData = (MyData) myData;
            newMyData.read();
        } else if (myData instanceof MyDataSafety) {
            MyDataSafety newMyData = (MyDataSafety) myData;
            newMyData.read();
        }
        countDownLatch.countDown();
    }
}

/**
 * 写task
 */
class MyDataWriteTask implements Runnable {

    private Object myData;
    private Integer item;

    private CountDownLatch countDownLatch;

    public MyDataWriteTask(Object myData, Integer item, CountDownLatch countDownLatch) {
        this.myData = myData;
        this.item = item;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if (myData instanceof MyData) {
            MyData newMyData = (MyData) myData;
            newMyData.write(item);
        } else if (myData instanceof MyDataSafety) {
            MyDataSafety newMyData = (MyDataSafety) myData;
            newMyData.write(item);
        }
        countDownLatch.countDown();
    }
}

class MyData {

//        private ConcurrentHashMap<Integer, Integer> data = new ConcurrentHashMap<>();
//        private Map<Integer, Integer> data = new ConcurrentHashMap<>();
    private Map<Integer, Integer> data = new HashMap<>();

    public void read() {
        if (data != null && data.size() > 0) {
            System.out.println("=============read:" + data);
        } else {
            System.out.println("data is empty.");
        }
    }

    public void write(Integer item) {
        data.put(item, item);
        System.out.println("=============write:" + data.size());
    }

    public Map<Integer, Integer> getData() {
        return data;
    }
}

/**
 * 使用读写锁控制并发写读
 */
class MyDataSafety {

    private Map<Integer, Integer> data = new HashMap<>(); // HashMap是非线程安全的，可使用ReadWriteLock控制并发读写，达到ConcurrentHashMap效果

    // 读写锁
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void read() {
        if (data != null && data.size() > 0) {
            rwl.readLock().lock();
            System.out.println("=============read:" + data);
            rwl.readLock().unlock();
        } else {
            System.out.println("data is empty.");
        }
    }

    public void write(Integer item) {
        rwl.writeLock().lock();
        data.put(item, item);
        System.out.println("=============write:" + data.size());
        rwl.writeLock().unlock();
    }

    public Map<Integer, Integer> getData() {
        return data;
    }
}
