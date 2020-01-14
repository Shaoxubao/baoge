package com.baoge.nio_reactor.reactore_multiplie_thread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 14:13
 *
 * 多线程模型，就是1个线程Acceptor接受客户端的连接，然后由一组IO线程（Reactor）来执行网络的读写
 *
 * demo中：
 * 其中NioServer中的Acceptor为接受客户端连接线程。
 * 其中NioReactorThreadGroup为一组IO线程，NioReactorThread为具体IO线程的实现。
 *
 */
public class NioServer {

    public static void main(String[] args) {

        new Thread(new Acceptor()).start();

    }

    /**
     * 连接线程模型，反应堆，转发器 Acceptor
     */
    private static final class Acceptor implements Runnable {

        private NioReactorThreadGroup nioReactorThreadGroup;

        public Acceptor() {
            nioReactorThreadGroup = new NioReactorThreadGroup();
        }

        public void run() {
            System.out.println("服务端启动成功，等待客户端接入");
            ServerSocketChannel ssc = null;
            Selector selector = null;
            try {
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress("127.0.0.1", 9080));

                selector = Selector.open();
                ssc.register(selector, SelectionKey.OP_ACCEPT);

                Set<SelectionKey> ops = null;
                while (true) {
                    try {
                        selector.select(); // 如果没有感兴趣的事件到达，阻塞等待
                        ops = selector.selectedKeys();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        break;
                    }

                    // 处理相关事件
                    for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                        SelectionKey key = it.next();
                        it.remove();

                        try {
                            if (key.isAcceptable()) { // 客户端建立连接
                                System.out.println("收到客户端的连接请求。。。");
                                ServerSocketChannel serverSc = (ServerSocketChannel) key.channel(); // 这里其实，可以直接使用ssl这个变量
                                SocketChannel clientChannel = serverSc.accept();
                                clientChannel.configureBlocking(false);
                                nioReactorThreadGroup.dispatch(clientChannel); // 转发该请求
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                            System.out.println("客户端主动断开连接。。。。。。。");
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
