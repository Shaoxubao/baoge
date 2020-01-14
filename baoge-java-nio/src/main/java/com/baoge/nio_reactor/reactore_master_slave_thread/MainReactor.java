package com.baoge.nio_reactor.reactore_master_slave_thread;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 9:34
 *
 * 主Reactor,主要用来处理连接请求的反应堆
 */
public class MainReactor implements Runnable {

    private Selector selector;
    private SubReactorThreadGroup subReactorThreadGroup;

    public MainReactor(SelectableChannel channel) {
        try {
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        subReactorThreadGroup = new SubReactorThreadGroup(4);
    }

    public void run() {

        System.out.println("MainReactor is running");
        // TODO Auto-generated method stub
        while (!Thread.interrupted()) {

            Set<SelectionKey> ops = null;
            try {
                selector.select(1000);
                ops = selector.selectedKeys();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
                        subReactorThreadGroup.dispatch(clientChannel); // 转发该请求
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    System.out.println("客户端主动断开连接。。。。。。。");
                }

            }

        }

    }
}
