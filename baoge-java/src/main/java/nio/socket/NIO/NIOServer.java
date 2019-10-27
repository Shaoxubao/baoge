package nio.socket.NIO;

import nio.socket.BIO.HandlerExecutorPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/10/27
 */
public class NIOServer {

    static HandlerExecutorPool executorPool = new HandlerExecutorPool(25,50, 1000,
            TimeUnit.SECONDS, new LinkedBlockingQueue());

    // jvm和服务器之间的通道
    private static ServerSocketChannel serverSocketChannel = null;
    public static Selector selector;

    public static void main(String[] args) throws Exception {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false); // NIO 非阻塞

        System.out.println("NIO Startup。");

        // Selector 一个线程处理多个连接
        selector = Selector.open();

        // 通过Selector，告诉操作系统底层，获取刚建立连接的socket
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int select = selector.select(1000L);
            if (select == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey result = iterator.next();
                if (result.isAcceptable()) { // 判断是否是新连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 等待数据传输
                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (result.isReadable()) {
                    SocketChannel channel = (SocketChannel) result.channel();
                    channel.configureBlocking(false);

                    // 告诉Selector，不需要再监听了
                    result.cancel();

                    // 业务处理... 线程池处理IO
                    executorPool.execute(new NIOSocketServerProcessor(channel));
                }

            }

            selectionKeys.clear();
            selector.selectNow();

        }

    }

}

class NIOSocketServerProcessor implements Runnable {

    private SocketChannel socketChannel;

    public NIOSocketServerProcessor(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {

        // 读取客户端请求内容
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        try {
            socketChannel.read(requestBuffer);
            requestBuffer.flip();
            byte[] array = requestBuffer.array();
            System.out.println("收到请求内容:" + new String(array));
            requestBuffer.clear();

            String response = "baooge";
            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
            socketChannel.write(responseBuffer);
            responseBuffer.clear();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.configureBlocking(false);
                socketChannel.register(NIOServer.selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
