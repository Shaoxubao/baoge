package com.baoge.jvm.bytecode.dynamicproxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/7
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("From real subject.");
    }
}
