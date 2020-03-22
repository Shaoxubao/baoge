package com.baoge.jvm.gc.g1;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/22
 *
 * VM参数：
    -verbose:gc
    -Xms10M
    -Xmx10M
    -XX:+UseG1GC
    -XX:+PrintGCDetails
    -XX:+PrintGCDateStamps
    -XX:MaxGCPauseMillis=200m
 */
public class MyTest {

    public static void main(String[] args) {
        int size = 1024 * 1024; // 1M

        byte[] myAlloc1 = new byte[size];
        byte[] myAlloc2 = new byte[size];
        byte[] myAlloc3 = new byte[size];
        byte[] myAlloc4 = new byte[size];

        System.out.println("hello world");
    }

}
