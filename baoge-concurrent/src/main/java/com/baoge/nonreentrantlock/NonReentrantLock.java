package com.baoge.nonreentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author shaoxubao
 * @Date 2019/12/5 14:19
 *
 * 基于AQS实现不可重入的独占锁(内部类继承AQS, 需重写tryAcquire, tryRelease, isHeldExclusively方法)
 */
public class NonReentrantLock implements Lock, java.io.Serializable {

    // 内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer {

        // 是否锁已经被持有
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 如果state为0，则尝试获取锁
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        // 尝试释放锁，设置state为0
        public boolean tryRelease(int releases) {
            assert releases == 1;

//            if (getState() == 0 || Thread.currentThread() != getExclusiveOwnerThread()) {
//                throw new IllegalMonitorStateException();
//            }

            //清空独占模式的拥有者
            setExclusiveOwnerThread(null);
            setState(0);

            return true;
        }

        // 提供条件变量接口
        Condition newCondition() {
            return new ConditionObject();
        }

    }

    // 创建一个Sync来做具体的操作
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeOut, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeOut));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
