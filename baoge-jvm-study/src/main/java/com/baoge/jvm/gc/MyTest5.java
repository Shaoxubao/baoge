package com.baoge.jvm.gc;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/20
 *
 * VM参数：
    -verbose:gc
    -Xms20M
    -Xmx20M
    -Xmn10M
    -XX:+PrintGCDetails
    -XX:SurvivorRatio=8
    -XX:+UseConcMarkSweepGC
 */
public class MyTest5 {

    public static void main(String[] args) {
        int size = 1024 * 1024; // 1M

        byte[] myAlloc1 = new byte[4 * size];

        System.out.println("111111");

        byte[] myAlloc2 = new byte[4 * size];

        System.out.println("222222");

        byte[] myAlloc3 = new byte[4 * size];

        System.out.println("333333");

        byte[] myAlloc4 = new byte[2 * size];

        System.out.println("444444");
    }

}
