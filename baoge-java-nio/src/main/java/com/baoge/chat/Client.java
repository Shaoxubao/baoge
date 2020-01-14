package com.baoge.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
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
 * @Date 2020/1/14 14:30
 */
public class Client {

    static ByteBuffer buffer = ByteBuffer.allocate(1024);
    // 记录昵称是否设置成功
    volatile static boolean success = false;
    // 用户昵称
    volatile static String name = "sxh";
    /**
     * 读消息
     * @param channel
     * @return
     * @throws IOException
     */
    private static String rec(SocketChannel channel) throws IOException {
        buffer.clear();
        int count = channel.read(buffer);
        buffer.flip();
        return new String(buffer.array(), 0, count, StandardCharsets.UTF_8);
    }
    /**
     * 写消息
     * @param msg
     * @param channel
     * @throws IOException
     */
    private static void write(String msg, SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put(msg.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        channel.write(buffer);
    }
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",7777));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 开启新线程，从服务端读取消息
        new Thread(() -> {
            SocketChannel client = null;
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            client = (SocketChannel) key.channel();
                            String msg = rec(client);
                            // 昵称设置成功
                            if (msg.contains("hello")) {
                                // 标识置为true
                                success = true;
                                name = msg.substring(6);
                            }
                            System.out.println(msg);
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                    selectionKeys.clear();
                } catch (IOException e) {
                    if (client != null) {
                        try {
                            client.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        // 主线程，用于写消息给服务端
        Scanner scanner = new Scanner(System.in);
        String tmp = "";
        while (true) {
            tmp = scanner.nextLine();
            if (success) {  // 昵称设置成功，开始聊天
                // 单聊（[消息]@[接收人]）
                if (tmp.contains("@")) {
                    tmp = tmp.replace("@", "###");
                }
                write(name + "###" + tmp, socketChannel);
            } else {    // 昵称尚未设置成功，继续设置
                write(tmp, socketChannel);
            }
        }
    }

}
