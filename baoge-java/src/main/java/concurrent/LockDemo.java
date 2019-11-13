package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    /**
     * volatile达不到效果，volatile关键字能保证可见性没有错，但是下面的程序错在没能保证原子性。
     * 可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的操作的原子性。
     * 达到效果需使用：synchronized或Lock、AtomicInteger，见后续。。。
     */
    public int count = 0;

    public Lock lock = new ReentrantLock();

    public void addCount() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final LockDemo demo = new LockDemo();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        demo.addCount();
                    }
                }
            }.start();
        }
        while (Thread.activeCount()>1) { // 保证前面的线程都执行完
            Thread.yield();
        }
        System.out.println(demo.count);
    }

}
