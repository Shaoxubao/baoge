package com.baoge.jvm.gc;

/**
 * java -XX:+PrintCommandLineFlags -version
 * 终端运行上面命令打印出：
 * -XX:InitialHeapSize=67108864 -XX:MaxHeapSize=1073741824 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPoints
 * -XX:+UseCompressedOops -XX:+UseParallelGC
 * 解释：
 * -XX:+UseParallelGC------>表示默认指定MyTest1注释的两种垃圾收集器
 *
 * VM参数配置:
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M：堆新生代大小
 * -XX:+PrintGCDetails:打印垃圾回收详细信息
 * -XX:SurvivorRatio=8----->eden：survivor = 8:1
 * -XX:PretenureSizeThreshold=4194304(4M)------>当创建对象大小超过指定阈值，直接进入老年代分配内存,
 *                                              搭配串行垃圾收集器使用（-XX:+UseSerialGC）
 *
 */
public class MyTest2 {

    public static void main(String[] args) {
        int size = 1024 * 1024; // 1M

        byte[] myAlloc = new byte[5 * size]; // 直接进入老年代(超过-XX:PretenureSizeThreshold=4194304(4M)设置的阈值),
                                             // 若改成8M,不是用-XX:+UseSerialGC参数，也会直接进入老年代，因为eden区容纳不了,
                                             // 若改为10M，则出现对内存溢出异常

    }

}
