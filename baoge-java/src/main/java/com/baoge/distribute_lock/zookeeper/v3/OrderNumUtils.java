package com.baoge.distribute_lock.zookeeper.v3;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/16
 */
public class OrderNumUtils {

    private static int num = 0; // 定义static，全局

    public int getOrderNum() {
        return ++num;
    }

}
