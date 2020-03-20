package com.baoge.distribute_lock.curator;

import org.apache.commons.lang3.RandomUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/3/20 14:57
 *
 * 原理就是：当某客户端要进行逻辑的加锁时，就在zookeeper上的某个指定节点的目录下，去生成一个唯一的临时有序节点， 然后判断自己是否是这些有序节点中序号最小的一个，
 * 如果是，则算是获取了锁。如果不是，则说明没有获取到锁，那么就需要在序列中找到比自己小的那个节点，并对其调用exist()方法，对其注册事件监听，当监听到这个节点被删除了，
 * 那就再去判断一次自己当初创建的节点是否变成了序列中最小的。如果是，则获取锁，如果不是，则重复上述步骤。
 * 当释放锁的时候，只需将这个临时节点删除即可。
 * Zookeeper本身很容易部署成集群模式，因此通过集群部署Zookeeper可以解决分布式锁的单点故障问题。
 *
 */
public class LockCuratorTest {

    private static final Logger logger = LoggerFactory.getLogger(LockCuratorTest.class);
    static int index = 0;

    private static final int SESSION_TIMEOUT = 10000;
    private static final String CONNECTION_STR = "localhost:2181";
    private static final String LOCK_ROOT = "/lock_curator_test";
    private static final int CONNECT_TIMEOUT = 8000;

    public static void main(String[] args) {

        final int threadCount = 20;
        CuratorHolder curatorHolder = initCuratorHolder();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    index++;
                    CuratorFramework client = curatorHolder.getClient();
                    if (client.getState() != CuratorFrameworkState.STARTED) {
                        client.start();
                    }
                    InterProcessLock lock = new InterProcessMutex(client, LOCK_ROOT);
                    boolean lockSuccess = false;

                    long threadId = Thread.currentThread().getId();
                    logger.info("---tryingToAcquire,threadId={}", threadId);

                    try {
                        lockSuccess = lock.acquire(1, TimeUnit.SECONDS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    logger.info(">>>>>threadId={}, lock_success={}, threadIndex={}", new Object[]{threadId, lockSuccess, index + ""});

                    if (lockSuccess) {
                        try {
                            Thread.sleep(RandomUtils.nextInt(10, 20));
                            lock.release();
                            logger.info(">>>>>threadId={}, lock_released={}, threadIndex={}", new Object[]{threadId, lockSuccess, index});
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }

//                        if (client.getState() == CuratorFrameworkState.STARTED) {
//                            logger.info("---Stop CuratorClient---,thredId={}", threadId);
//                            CloseableUtils.closeQuietly(client);
//                        }
                    CloseableUtils.closeQuietly(client);
                }
            });
//            threadPool.submit(thread);
            thread.start();
        }
    }

    private static CuratorHolder initCuratorHolder() {
        CuratorHolder curatorHolder = new CuratorHolder();
        ZKConfigBean zkConfigBean = new ZKConfigBean();

        zkConfigBean.setConnectStr(CONNECTION_STR);
        zkConfigBean.setLockRoot(LOCK_ROOT);
        zkConfigBean.setSessionTimeout(SESSION_TIMEOUT);
        zkConfigBean.setConnectTimeout(CONNECT_TIMEOUT);
        curatorHolder.setZkConfigBean(zkConfigBean);

        return curatorHolder;
    }

}
