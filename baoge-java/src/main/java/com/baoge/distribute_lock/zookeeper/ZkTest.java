package com.baoge.distribute_lock.zookeeper;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @Author shaoxubao
 * @Date 2020/3/20 15:37
 */
public class ZkTest {

    private static String lockRootValue = "/lock_zk_test";
    private static int sessionTimeoutValue = 10000;
    private static String connectionStringValue = "localhost:2181";

    public static void main(String[] args) {


        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    DistributedLock lock = null;
                    try {
                        lock = new DistributedLock(lockRootValue, connectionStringValue, sessionTimeoutValue);
                        latch.countDown();
                        latch.await();
                        lock.lock();
                        Thread.sleep(RandomUtils.nextInt(200, 500));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (lock != null) {
                            lock.unlock();
                        }
                    }
                }
            }).start();
        }
    }

}
