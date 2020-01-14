package com.baoge.nio_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 9:43
 */
public class SocketClient {

    public static void main(String[] args) throws Exception {

        SocketChannel clientClient = SocketChannel.open();
        clientClient.configureBlocking(false);

        Selector selector = Selector.open();

        clientClient.register(selector, SelectionKey.OP_CONNECT);
        clientClient.connect(new InetSocketAddress("127.0.0.1",12345));

        new Thread(() -> {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> ops = selector.selectedKeys();
                for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isConnectable()) {
                        System.out.println("client connect");
                        SocketChannel sc =  (SocketChannel) key.channel();
                        // 判断此通道上是否正在进行连接操作。
                        // 完成套接字通道的连接过程。
                        if (sc.isConnectionPending()) {
                            sc.finishConnect();
                            System.out.println("完成连接!");
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            buffer.put("Hello, Server, I already Connect to you.".getBytes());
                            buffer.flip();
                            sc.write(buffer);
                        }
                        sc.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        System.out.println("客户端收到服务器的响应....");
                        SocketChannel sc = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int count = sc.read(buffer);
                        if (count > 0 ) {
                            buffer.flip();
                            byte[] response = new byte[buffer.remaining()];
                            buffer.get(response);
                            System.out.println(URLDecoder.decode(new String(response), "UTF-8"));

                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }

                ops.clear();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        }
        }).start();

        Thread.sleep(2000);

        // 主线程，用于写消息给服务端
        System.out.println("请输入：");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String msg = "";
            msg = scanner.nextLine();

            if (msg.equals("q")) {
                break;
            }

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.clear();
            buffer.put(msg.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            clientClient.write(buffer);
        }
    }

}
