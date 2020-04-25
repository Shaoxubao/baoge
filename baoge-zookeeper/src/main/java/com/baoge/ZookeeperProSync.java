package com.baoge;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/25
 */

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Zookeeper配置中心
 * cmd启动配置好的zkServer，然后启动代码（两个客户端），cmd再启动zkClient命令操作节点数据：
 * create /baoge baoge66
 * get /baoge
 * set /baoge
 */
public class ZookeeperProSync implements Watcher {

    // zk配置数据存放路径
    private static String ZK_PATH = "/baoge";

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zk;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        // 连接zk，并注册一个默认的监听器
        zk = new ZooKeeper("192.168.1.102:2181", 5000, new ZookeeperProSync());
        // 等待zk连接成功的通知
        connectedSemaphore.await();
        // 获取path目录节点的配置数据，
        System.out.println(new String(zk.getData(ZK_PATH, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }


    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) { // zk连接成功的通知事件
            if (Event.EventType.None == event.getType() && null ==event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) { // 节点数据变化通知事件
                try {
                    System.out.println("配置已修改为：" +
                            new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
