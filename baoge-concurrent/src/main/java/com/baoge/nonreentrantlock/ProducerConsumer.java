package com.baoge.nonreentrantlock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * @Author shaoxubao
 * @Date 2019/12/5 14:44
 *
 * 用自定义锁实现一个简单地生产者-消费者模型
 */
public class ProducerConsumer {

    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingDeque<String>();
    final static int queueSize = 10;

    public static void main(String[] args) {

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 获取独占锁
                    lock.lock();

                    try {
                        // 如果队列满了则等待
                        if (queueSize == queue.size()) {
                            notEmpty.await();
                        } else {

                            // 向队列添加元素
                            String pro = "baoge";
                            queue.add(pro);
                            System.out.println("================producer :" + pro);

                            Thread.sleep(1000);

                            // 唤醒消费线程
                            notFull.signalAll();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });


        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 获取独占锁
                    lock.lock();

                    try {
                        // 如果队列为空则等待
                        if (queue.size() == 0) {
                            notFull.await();
                        } else {

                            // 消费元素
                            String result = queue.poll();
                            Thread.sleep(1000);
                            System.out.println("================consumer :" + result);

                            // 唤醒生产线程
                            notEmpty.signalAll();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });


        // 启动线程
        producer.start();
        consumer.start();

    }

}
