package com.baoge.distribute_lock.zookeeper.v3.zk_lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/16
 */
public abstract class AbstractZKLock implements ZKLock {

    public static final String ZK_SERVER = "127.0.0.1:2181";
    public static final int ZK_TIME_OUT = 60 * 1000;

    protected String LOCK_PATH = "/ZK_LOCK_6666";

    protected CountDownLatch LATCH = null;

    ZkClient zkClient = new ZkClient(ZK_SERVER, ZK_TIME_OUT);

    @Override
    public void lock() {
        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + " 占用锁成功.");
        } else {
            waitLock(); // 等待

            lock();     // 继续枪锁
        }
    }

    @Override
    public void unLock() {
        if (zkClient != null) {
            zkClient.close();
        }

        System.out.println(Thread.currentThread().getName() + " 释放锁成功.");
    }

    public abstract boolean tryLock();
    public abstract void waitLock();

}
