package com.baoge.distribute_lock.zookeeper.v3.zk_lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/16
 */
public class ZKDistributedLock extends AbstractZKLock {

    @Override
    public boolean tryLock() {

        try {
            zkClient.createEphemeral(LOCK_PATH);

            return true;
        } catch (RuntimeException e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void waitLock() {

        IZkDataListener dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                if (LATCH != null) {
                    LATCH.countDown(); // 解除等待
                }
            }
        };

        zkClient.subscribeDataChanges(LOCK_PATH, dataListener);

        if (zkClient.exists(LOCK_PATH)) {
            try {
                LATCH = new CountDownLatch(1);
                LATCH.await(); // 等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 解除监听
        zkClient.unsubscribeDataChanges(LOCK_PATH, dataListener);
    }
}
