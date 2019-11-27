package com.baoge.locksupport;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shaoxubao
 * @Date 2019/11/27 14:16
 *
 * 锁和同步类的基础;
 * LockSupport主要是挂起和唤醒线程，内部是Unsafe实现的.
 */
public class LockSupportDemo {

    @Test
    public void testLockSupport1() {

        System.out.println("begin park!");

        LockSupport.park(); // 阻塞，不往下执行

        System.out.println("end park!");
    }

    @Test
    public void testLockSupport2() {

        System.out.println("begin park!");

        LockSupport.unpark(Thread.currentThread()); // 关联当前线程，使当前线程获取许可证
        LockSupport.park();                         // 不会阻塞，继续往下执行

        System.out.println("end park!");
    }

    @Test
    public void testLockSupport3() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park.");

                LockSupport.park(); // 调用park,挂起自己

                System.out.println("child thread end park.");
            }
        });

        thread.start(); // 启用子线程

        Thread.sleep(1000);

        System.out.println("main thread begin unpark.");

        LockSupport.unpark(thread); // 让thread持有许可证

    }

    @Test
    public void testLockSupport4() throws InterruptedException {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park.");

                // 调用park方法，挂起自己，只有被中断才会退出循环
                while (!Thread.currentThread().isInterrupted()) {
                    LockSupport.park();
                }

                System.out.println("child thread end park.");
            }
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin unpark.");

        thread.interrupt(); // 中断子线程

    }

}
