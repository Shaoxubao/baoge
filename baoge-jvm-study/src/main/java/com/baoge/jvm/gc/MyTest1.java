package com.baoge.jvm.gc;

/**
 * VM参数配置:
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M：堆新生代大小
 * -XX:+PrintGCDetails:打印垃圾回收详细信息
 * -XX:SurvivorRatio=8：eden：survivor = 8:1
 *
 * jdk1.8默认使用：
 * PSYoungGen:Parallel Scavenge(新生代垃圾收集器)
 * ParOldGen:Parallel Old (老年代垃圾收集器)
 */

public class MyTest1 {

    public static void main(String[] args) {
        int size = 1024 * 1024; // 1M
        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[3 * size];
//        byte[] myAlloc4 = new byte[2 * size];

        System.out.println("hello world");
    }

}
