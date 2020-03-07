package com.baoge.jvm.bytecode.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/7
 *
 * 最终调用sun.misc.ProxyGenerator#generateProxyClass(java.lang.String, java.lang.Class[], int)生成字节码,如果
 * 设置系统属性sun.misc.ProxyGenerator.saveGeneratedFiles=true,将字节码文件保存到磁盘,如下实例：
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        RealSubject realSubject = new RealSubject();
        InvocationHandler handler = new DynamicSubject(realSubject);
        Class<?> cls = realSubject.getClass();

        Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), handler);

        subject.request();

        // class com.sun.proxy.$Proxy0 程序运行期动态生成
        System.out.println(subject.getClass());
        System.out.println(subject.getClass().getSuperclass()); // class java.lang.reflect.Proxy
    }

}
