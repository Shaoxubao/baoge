package com.baoge.distribute_lock.zookeeper.v3.zk_lock;

import com.baoge.distribute_lock.zookeeper.v3.OrderService;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/16
 */
public class Client {

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                new OrderService().getOrderNum();
            }, String.valueOf(i)).start();
        }

    }

}
