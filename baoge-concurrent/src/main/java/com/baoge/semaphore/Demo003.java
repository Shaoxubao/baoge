package com.baoge.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/2/20 14:46
 *
 * semaphore初始化有2个令牌，一个线程调用1次release方法，然后一次性获取3个令牌，会获取到吗? 答案：能，原因是release会添加令牌，并不会以初始化的大小为准。
 * Semaphore中release方法的调用并没有限制要在acquire后调用。
 */
public class Demo003 {

    public static void main(String[] args) {
        int permitsNum = 2;
        final Semaphore semaphore = new Semaphore(permitsNum);
        try {
            System.out.println("availablePermits:" + semaphore.availablePermits() +
                    ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3,1, TimeUnit.SECONDS));
            semaphore.release();
            System.out.println("availablePermits:" + semaphore.availablePermits() +
                    ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3,1, TimeUnit.SECONDS));
        } catch (Exception e) {

        }
    }
}
