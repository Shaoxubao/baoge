package com.baoge.jvm.bytecode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/7
 */
public class DynamicSubject implements InvocationHandler {

    private Object sub;

    public DynamicSubject(Object obj) {
        this.sub = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before call ======= " + method);
        method.invoke(this.sub, args);
        System.out.println("after  call ======= " + method);

        return null;
    }
}
