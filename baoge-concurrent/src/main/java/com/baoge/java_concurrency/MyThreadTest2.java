package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/5
 */
public class MyThreadTest2 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        Thread t1 = new MyThread1(myClass);
        Thread t2 = new MyThread2(myClass);

        t1.start();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start(); // 最终输出：hello
                    //          world
    }
}

class MyClass {
    public synchronized void hello() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}

class MyThread1 extends Thread {

    private MyClass myClass;

    public MyThread1(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class MyThread2 extends Thread {

    private MyClass myClass;

    public MyThread2(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.world();
    }
}
