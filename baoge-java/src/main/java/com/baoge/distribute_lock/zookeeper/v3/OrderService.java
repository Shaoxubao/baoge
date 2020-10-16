package com.baoge.distribute_lock.zookeeper.v3;

import com.baoge.distribute_lock.zookeeper.v3.zk_lock.ZKDistributedLock;
import com.baoge.distribute_lock.zookeeper.v3.zk_lock.ZKLock;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/16
 */
public class OrderService {

    private OrderNumUtils orderNumUtils = new OrderNumUtils();

    private ZKLock lock = new ZKDistributedLock();

    public void getOrderNum() {

        try {
            lock.lock();

            System.out.println("获得订单编号------->" + orderNumUtils.getOrderNum());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }

}
