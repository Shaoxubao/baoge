package com.baoge.distribute_lock.redis;

/**
 * @Author shaoxubao
 * @Date 2020/3/23 18:00
 */
public class Test {

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }

}
