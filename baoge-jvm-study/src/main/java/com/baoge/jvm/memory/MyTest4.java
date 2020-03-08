package com.baoge.jvm.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/8
 *
 * 方法区产生内存溢出错误：
 * 先阐述一下怎么才能做到让方法区溢出，下次再详细编写代码：其实不给JVM配置一些参数让方法区溢出是挺难办的，
 * 因为自于JDK1.8开始方法区废除了永久代采用了元空间，而元空间是采用操作系统的本地内存，而且默认情况下它的
 * 初始内存大小是21MB，并且随着内存的不断占用如果达到了它的上限的话元空间虚拟机会进行垃圾回收，如果回收内存
 * 还不够的话就会对内存进行扩展，而且这个扩展可以一直扩展到物里内存的最大限度，所以咱们要想做这个实验需要
 * 采取两种手段结合起来才能模拟出来：第一个手段是显示的设置元空间的大小，让元空间不会自动扩展，第二个手段
 * 需要和手段一结合起来，这里就需要明确知道元空间里存放的是什么信息这样我们才能有目标的去想办法来生成这个
 * 信息往元空间里存放直接撑爆它，它里面存储的是元信息，换句话说就是它里面是不会存放对象的实例之类的，比如
 * 一个类的class的元信息就是存放在元空间里面的，那有什么办法来产生class的元信息呢，其实运行期动态生成类
 * 【像大名鼎鼎的spring这样的框架就有这种动态生成类的东东】就会往元空间存放信息，如Java动态代理、cglib库，
 * 下一次我们就用cglib库来不断动态生成类模拟元空间内存溢出的情况，这里先有一个感性的认识既可。
 *
 * VM配置：
 *  -XX:MaxMetaspaceSize=10M -XX:+TraceClassLoading
 * 运行程序之后抛出：java.lang.OutOfMemoryError: Metaspace
 * 用工具jconsole(vm配置 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false
    -Dcom.sun.management.jmxremote.authenticate=false，jconsole远程localhost:8099)观察.

    再用jvisualvm观察元空间变化
    https://www.infoq.cn/article/Java-PERMGEN-Removed（元空间是什么？）
 */
public class MyTest4 {

    public static void main(String[] args) {
        // 无限创建Class（不是实例），让其落入元空间
        for (; ; ) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MyTest4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) ->
                    proxy.invokeSuper(obj, args));

            System.out.println("hello world");
            enhancer.create(); // 在运行期不断的创建全新的MyTest4的子类，创建完把MyTest4的子类的元信息放入元空间
        }
    }

}
