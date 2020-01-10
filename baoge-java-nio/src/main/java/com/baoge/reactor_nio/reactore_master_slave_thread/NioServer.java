package com.baoge.reactor_nio.reactore_master_slave_thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 9:29
 *
 * Reactor 主从Reactor模式实现(Reactor反应堆设计模式实现（基于java.nio）)
 *
 * Acceeptor：职责维护java.nio.ServerSocketChannel类，绑定服务端监听端口，然后将该通道注册到MainRector中；
 * Main Reactor,监听客户端连接的反应堆，这里使用jdk并发中的Executors.newSingleThreadExecutor线程池来实现，监听客户端的连接事件(OP_ACCEPT)
 * Sub Reactor,目前没有使用jdk的并发池，这里用的SubReactorThreadGroup,其实现是数组，当然这里也可以使用jdk线程池，
 * SubReactor的每一个线程都是IO线程，用来处理读，写事件。所有的IO线程公用一个业务线程池（基于juc）实现，用来处理业务逻辑，也就是运行Handel的地方。
 * Handel:具体业务逻辑实现，本例就是获取客户端的信息后，在请求信息后面追加一段文字，便返回给客户端。
 */
public class NioServer {

    private static final int DEFAULT_PORT = 9080;

    public static void main(String[] args) {

        new Thread(new Acceptor()).start();

    }

    private static class Acceptor implements Runnable {

        // main Reactor 线程池，用于处理客户端的连接请求
        private static ExecutorService mainReactor = Executors.newSingleThreadExecutor();

        public void run() {
            ServerSocketChannel ssc = null;

            try {
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress(DEFAULT_PORT));

                // 转发到 MainReactor反应堆
                dispatch(ssc);

                System.out.println("服务端成功启动。。。。。。");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private void dispatch(ServerSocketChannel ssc) {
            mainReactor.submit(new MainReactor(ssc));
        }

    }
}
