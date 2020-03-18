package com.baoge.jvm.gc;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/18
 *
 * VM参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5 -XX:+PrintTenuringDistribution

   -XX:MaxTenuringThresHold=5 ——> 在可以自动调节对象晋升(Promote)到老年代阈值的GC中，设置该阈值的最大值
                                  该参数默认值为15，CMS中默认值为6，G1中默认值为15(在JVM中，该数值是由4个bit来表示的，所以最大值是1111，即15)，
 经历了多次GC后，存活的对象会在From Survivor和To Survivor之间来回存放，而这里面的一个前提则是这俩空间有足够的大小来存放这些数据，
 在GC算法中，会计算每个对象年龄的大小，如果达到某个年龄后发现总大小已经大于了一个Survivor空间的50%【也就是在Survivor中有一半以上的空间
 都已经被多次GC依然没有被回收的对象所占据了】，那么这时候就需要调整阈值，不能再继续等到默认的15次GC后才完成晋升，因为这样会导致Survivor
 空间不足【这是一件非常可怕的事情，因为会导致很多新的对象直接就晋升到了老年代】，所以需要调整阈值，让这些存活对象尽快完成晋升，来释放Survivor空间。
   -XX:+PrintTenuringDistribution ——> 表示打印年龄为几的对象占多少字节的一些详细信息。
 */
public class MyTest3 {

    public static void main(String[] args) {
        int size = 1024 * 1024; // 1M
        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
        byte[] myAlloc4 = new byte[2 * size];

        System.out.println("hello world");
    }

}
