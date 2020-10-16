package com.baoge.distribute_lock.zookeeper.v3.zk_lock;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/16
 */
public interface ZKLock {

    void lock();

    void unLock();

}
