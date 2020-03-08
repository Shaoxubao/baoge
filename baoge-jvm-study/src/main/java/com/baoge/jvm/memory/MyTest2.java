package com.baoge.jvm.memory;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/3/8
 *
 * 虚拟机栈溢出
 * 指定堆栈大小：-Xss100k
 *
 * 同时VM配置：  -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
 * 再启动jconsole远程连接localhost:8099即可,如果用jvisualvm，可直接用idea继承插件
 *
 */
public class MyTest2 {

    private int lenth;

    public int getLenth() {
        return lenth;
    }

    public void test() {
        lenth += 1;

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test();
    }

    public static void main(String[] args) {
        MyTest2 test2 = new MyTest2();

        try {
            test2.test();
        } catch (Throwable e) {
            System.out.println(test2.getLenth());
            e.printStackTrace();
        }

    }

}
