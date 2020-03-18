package com.baoge.jvm.gc;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/18
 *
 * VM参数：
    -verbose:gc
    -Xmx200M
    -Xmn50M
    -XX:TargetSurvivorRatio=60
    -XX:+PrintTenuringDistribution
    -XX:+PrintGCDetails
    -XX:+PrintGCDateStamps
    -XX:+UseConcMarkSweepGC
    -XX:+UseParNewGC
    -XX:MaxTenuringThreshold=3

 参数解释：
 -XX:TargetSurvivorRatio=60：该参数的作用是当某一个survivor空间已经存活的对象如果已经占据了这个survivor空间的60%,
 那么会重新计算晋升的阈值而不是直接使用"MaxTenuringThreshold"配置的值，比如说Survivor空间是10M的大小，
 也就是说Survivor空间中已存活的对象的容量已经超过了6M的话，就会重新计算对象晋升的阈值了，而不是使用"MaxTenuringThreshold"所设置的值了
 */
public class MyTest4 {

    public static void main(String[] args) throws Exception {
        byte[] bytes1 = new byte[512 * 1024];
        byte[] bytes2 = new byte[512 * 1024];

        myGC();
        Thread.sleep(1000);
        System.out.println("111111"); //

        myGC();
        Thread.sleep(1000);
        System.out.println("222222"); //

        myGC();
        Thread.sleep(1000);
        System.out.println("333333"); //

        myGC();
        Thread.sleep(1000);
        System.out.println("444444"); //

        byte[] bytes3 = new byte[1024 * 1024];
        byte[] bytes4 = new byte[1024 * 1024];
        byte[] bytes5 = new byte[1024 * 1024];

        myGC();
        Thread.sleep(1000);
        System.out.println("555555"); //

        myGC();
        Thread.sleep(1000);
        System.out.println("666666"); //

        System.out.println("hello world");
    }

    private static void myGC() {
        for (int i = 0; i < 40; i++) {
            byte[] bytes = new byte[1024 * 1024];
        }
    }
}
