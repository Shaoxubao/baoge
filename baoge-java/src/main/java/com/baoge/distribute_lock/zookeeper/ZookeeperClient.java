package com.baoge.distribute_lock.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/3/20 15:33
 */
public class ZookeeperClient {

    public static ZooKeeper getInstance(String connectionString, int sessionTimeout) throws Exception {
        final CountDownLatch connectedSignal = new CountDownLatch(1);

        ZooKeeper zk = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });
        connectedSignal.await(sessionTimeout, TimeUnit.MILLISECONDS);
        return zk;
    }

}
