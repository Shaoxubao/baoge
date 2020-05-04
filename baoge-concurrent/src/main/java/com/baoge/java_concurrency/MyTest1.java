package com.baoge.java_concurrency;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/5/4
 *
 * 关于wait()&notify()&notifyAll()总结：
 *
 * 1、当调用wait()时，首先需要确保调用了wait()方法的线程已经持有了对象的锁。
 * 2、当调用wait()后，该线程就会释放掉这个对象的锁，然后进入到等待状态（wait set ——> 等待集合）。
 * 3、当线程调用了wait()后进入到等待状态时，它就可以等待其它线程调用相同对象的notify和notifyAll方法来使得自己被唤醒。
 * 4、一旦这个线程被其它线程唤醒后，该线程就会与其它线程一同开始竞争这个对象的锁（公平竞争），只有当该线程获取到了这个对象的锁后，线程才会继续往下执行。
 * 5、调用wait()方法的代码片段需要放在一个synchronized或或者是synchronized方法中，这样才可以确保线程在调用wait()方法前已经获取到了对象的锁。
 * 6、当调用对象的notify()方法时，它会随机唤醒该对象等待集合（wait set）中的任意一个线程，当某个线程被唤醒后，它就会与其它线程一同竞争对象的锁。
 * 7、当调用对象的notifyAll()方法时，它会唤醒该对象等待集合(wait set)中的所有线程，这些线程被唤醒后，又会开始竞争对象的锁。
 * 8、在某一时刻，只有唯一一个线程可以拥有对象的锁。
 */
public class MyTest1 {

    public static void main(String[] args) {
        Object object = new Object();

        try {
            synchronized (object) {
                // 持有Object对象锁的线程才可以这样调用wait()方法,然后会释放当前线程持有的对象锁（monitor），
                // 等待其他线程调用这个对象的notify()或notifyAll()方法后，等待的线程继续执行；
                // 而sleep()方法是会一直持有此对象锁一段时间的
                object.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
