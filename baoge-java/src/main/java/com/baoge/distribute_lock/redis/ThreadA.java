package com.baoge.distribute_lock.redis;

/**
 * @Author shaoxubao
 * @Date 2020/3/23 17:59
 */
public class ThreadA extends Thread {

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }

}
