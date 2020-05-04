package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/4
 *
 * 编写一个多线程程序，实现这样的一个目标：
 *
 * 1、存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0。
 *
 * 2、创建两个线程，其中一个线程对该对象的成员变量counter增1，另一个线程对该对象的成员变量减1。
 *
 * 3、输出该对象成员变量counter每次变化后的值。
 *
 * 4、最终输出的结果应为：101010101010。。。。
 */
public class MyTest2 {

    public static final int count = 5; // 控制打印次数

    public static void main(String[] args) {

        MyObject myObject = new MyObject();

        Thread increaseThread = new IncreaseThread(myObject);
        Thread increaseThread2 = new IncreaseThread(myObject);
        Thread decreaseThread = new DecreaseThread(myObject);
        Thread decreaseThread2 = new DecreaseThread(myObject);

        increaseThread.start(); // 两个线程启动顺序无所谓先后
        increaseThread2.start();
        decreaseThread.start();
        decreaseThread2.start();

    }

}

class IncreaseThread extends Thread {
    private MyObject myObject;

    public IncreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < MyTest2.count; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject.counterAdd();
        }
    }
}

class DecreaseThread extends Thread {
    private MyObject myObject;

    public DecreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < MyTest2.count; i++) {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject.counterSub();
        }
    }
}

class MyObject {

    private int counter = 0;

    public synchronized void counterAdd() {
        // 若counter不为0，wait释放锁，notify让减一线程执行,但是这里用if会有问题(如果是大于一个增加或减少线程时，
        // 有种情况是：两个减少线程都处于wait，一个增加线程增加后唤醒其中一个减线程，减线程减少后唤醒其他线程，若此时
        // 唤醒的是另一个减线程，此减线程再减操作就有问题了，因为此时counter是0，所以要把if改为while,唤醒后发现不满足
        // 条件继续wait)
//        if (counter != 0) {
        while (counter != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;

        System.out.println(counter);

        notify(); // 唤醒其他线程
    }

    public synchronized void counterSub() {
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;

        System.out.println(counter);

        notify(); // 唤醒其他线程
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
