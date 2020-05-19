package com.baoge.my_impl;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author shaoxubao
 * @Date 2020/5/18 11:01
 *
 * 手写简易实现Java线程池
 *
 * 目前存在问题待修复：
 *  1、不能优雅关闭线程池
 *  2、不支持拒绝策略
 *  3、不能监控线程各个指标
 */
public class MyThreadPool implements MyThreadPoolService {

    /**
     * 线程池状态
     */
    private volatile boolean isRunning = true;

    /**
     * 记录线程个数
     */
    private AtomicInteger threadCount = new AtomicInteger(0);

    /**
     * 核心线程数
     */
    private volatile int corePoolSize;

    /**
     * 阻塞队列
     */
    private final BlockingQueue<Runnable> workQueue;

    private final ReentrantLock mainLock = new ReentrantLock();

    private final Condition termination = mainLock.newCondition();

    /**
     * 工作线程
     */
    private final HashSet<WorkerThread> workers = new HashSet<>();


    public MyThreadPool(int corePoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.workQueue = workQueue;
    }

    /**
     * 执行任务
     */
    @Override
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        // 判断线程数量是否小于核心
        if (threadCount.get() < corePoolSize) {
            if (addWorker(command)) {
                return;
            }
        }

        // 添加到队列
        workQueue.offer(command);
    }

    private boolean addWorker(Runnable command) {
        // 自旋设置threadCount
        for (; ; ) {
            if (threadCount.compareAndSet(threadCount.get(), threadCount.get() + 1)) {
                break;
            }
        }

        // 构造任务及线程
        WorkerThread workerThread = new WorkerThread(command);
        final Thread t = workerThread.thread;

        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();

        boolean workerAdded = false;
        try {
            if (isRunning) {
                if (t.isAlive())
                    throw new IllegalThreadStateException();

            workers.add(workerThread);
            workerAdded = true;
            }
        } finally {
            mainLock.unlock();
        }

        // 启动任务
        if (workerAdded) {
            t.start();
        }

        return true;
    }

    /**
     * 关闭线程池
     */
    @Override
    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            System.out.println("shutdown===========");

            isRunning = false;
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    private void tryTerminate() {
        for (; ;) {
            for (WorkerThread w : workers) {
                Thread t = w.thread;
                System.out.println("=========线程" + t.getName() + "状态：" + t.getState());
                if (!t.isInterrupted() && w.tryLock()) {
                    try {
                        t.interrupt();
                        System.out.println("线程被中断:" + t.getName());
                    } finally {
                        w.unlock();
                    }
                }
            }

            termination.signalAll();
            return;
        }
    }


    /**
     * 工作线程(内部类)
     */
    public final class WorkerThread extends AbstractQueuedSynchronizer implements Runnable {

        final Thread thread;

        Runnable firstTask;

        public WorkerThread(Runnable firstTask) {
            setState(-1);
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorkerThread(this);
        }

        // ============================
        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock()        { acquire(1); }
        public boolean tryLock()  { return tryAcquire(1); }
        public void unlock()      { release(1); }
        public boolean isLocked() { return isHeldExclusively(); }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
        // ============================
    }

    final void runWorkerThread(WorkerThread workerThread) {
        Thread wt = Thread.currentThread();
        Runnable task = workerThread.firstTask;
        workerThread.firstTask = null;
        workerThread.unlock();

        try {
            // 让线程自旋一直取队列任务并执行
            while (task != null || (task = getTask()) != null) {
                workerThread.lock();

                if (!isRunning && !wt.isInterrupted()) {
                    wt.interrupt();
                }
                try {
                    task.run();
                    task = null; // 此处要设置task为null,不然会造成死循环
                } finally {
                    workerThread.unlock();
                }
            }
        } finally {
            processWorkerExit(workerThread);
        }
    }

    private Runnable getTask() {
        for (; ; ) {
            System.out.println("线程a" + Thread.currentThread().getName() + "状态：" + Thread.currentThread().getState());
            if (!isRunning || Thread.currentThread().isInterrupted()) {
                return null;
            }
            try {
                Runnable r = workQueue.isEmpty() ? workQueue.take() : workQueue.poll();
                if (r != null)
                    workQueue.remove(r);
                return r;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void processWorkerExit(WorkerThread workerThread) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();

        try {
            workers.remove(workerThread);
            System.out.println("WorkerThread is removed");
        } finally {
            mainLock.unlock();
        }

        tryTerminate();
    }

    public HashSet<WorkerThread> getWorkers() {
        return workers;
    }
}
