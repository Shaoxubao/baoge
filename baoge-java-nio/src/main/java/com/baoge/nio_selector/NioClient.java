package com.baoge.nio_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 9:43
 */
public class NioClient {

    public static void main(String[] args) {

        SocketChannel clientClient;
        Selector selector = null;
        try {
            clientClient = SocketChannel.open();
            clientClient.configureBlocking(false);

            selector = Selector.open();

            clientClient.register(selector, SelectionKey.OP_CONNECT);

            clientClient.connect(new InetSocketAddress("127.0.0.1",12345));

            Set<SelectionKey> ops = null;

            while (true) {
                try {
                    selector.select();
                    ops = selector.selectedKeys();
                    for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                        SelectionKey key = it.next();
                        it.remove();
                        if(key.isConnectable()) {
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
                            sc.register(selector, SelectionKey.OP_WRITE);
                        } else if(key.isWritable()) {
                            System.out.println("客户端写:");
                            SocketChannel sc = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);

                            sc.register(selector, SelectionKey.OP_READ);
                            while (true) {
                                buffer.clear();
                                String message = new Scanner(System.in).nextLine();
                                buffer.put(message.getBytes());
                                buffer.flip();
                                sc.write(buffer);

                                if (message.contains("over")) {
                                    break;
                                }
                            }
                        } else if(key.isReadable()) {
                            System.out.println("客户端收到服务器的响应....");
                            SocketChannel sc = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int count = sc.read(buffer);
                            if(count > 0 ) {
                                buffer.flip();
                                byte[] response = new byte[buffer.remaining()];
                                buffer.get(response);
                                System.out.println(URLDecoder.decode(new String(response), "UTF-8"));
                            } else {
//                                key.cancel();
//                                sc.close();
                                sc.register(selector, SelectionKey.OP_WRITE);
                            }

                        }

                    }

                } catch(Throwable e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
