package com.baoge.distribute_lock.zookeeper;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @Author shaoxubao
 * @Date 2020/3/20 15:37
 */
public class ZkTest {

    private static String LOCK_ROOT_VALUE = "/lock_zk_test";
    private static int SESSION_TIMEOUT_VALUE = 10000;
    private static String CONNECTION_STRING_VALUE = "localhost:2181";

    public static void main(String[] args) {


        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    DistributedLock lock = null;
                    try {
                        lock = new DistributedLock(LOCK_ROOT_VALUE, CONNECTION_STRING_VALUE, SESSION_TIMEOUT_VALUE);
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
