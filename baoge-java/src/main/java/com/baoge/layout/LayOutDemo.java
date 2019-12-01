package com.baoge.layout;

import org.openjdk.jol.info.ClassLayout;
import utils.HashUtil;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/1
 *
 * 查看对象字节码信息
 */
public class LayOutDemo {

    static L l = new L();

    public static void main(String[] args) {

        System.out.println("start");

        int hashCode = l.hashCode(); // 生成hashcode
        System.out.println(Integer.toHexString(hashCode));
        HashUtil.calculateHashCode(l);

        // 查看对象头信息
        System.out.println(ClassLayout.parseInstance(l).toPrintable());

        synchronized (l) { // 锁住的是对象
            System.out.println("lock ing...");
        }


        System.out.println("end.");

    }

}
