package com.baoge.nio_chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author shaoxubao
 * @Date 2020/6/24 10:20
 *
 * 群聊的功能点：
 *
 * （1）加入群聊，并通知其他人；
 * （2）发言，并通知其他人；
 * （3）退出群聊，并通知其他人；
 * （4）加了单聊功能；
 *
 * 打开四个CRT客户端，分别连接telnet 127.0.0.1 8080，然后就可以开始群聊测试
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        // 将accept事件绑定到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started!");

        while (true) {
            // 阻塞在select上
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历selectKeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 如果是accept事件
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    System.out.println("accept new conn: " + socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 加入群聊
                    ChatHolder.join(socketChannel);
                } else if (selectionKey.isReadable()) {
                    // 如果是读取事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 将数据读入到buffer中
                    int length = socketChannel.read(buffer);
                    if (length > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        // 将数据读入到byte数组中
                        buffer.get(bytes);

                        // 换行符会跟着消息一起传过来
                        String content = new String(bytes, "UTF-8").replace("\r\n", "");
                        if (content.equalsIgnoreCase("quit")) {
                            // 退出群聊
                            ChatHolder.quit(socketChannel);
                            selectionKey.cancel();
                            socketChannel.close();
                        } else if (content.contains(":")) {
                            // 单聊
                            ChatHolder.singleChat(socketChannel, content);
                        } else {
                            // 扩散
                            ChatHolder.propagate(socketChannel, content);
                        }
                    }
                }
                iterator.remove();
            }
        }
    }

    // 这是一个静态内部类
    private static class ChatHolder {
        // 记录SocketChannel与用户Id关系
        static final Map<SocketChannel, String> CHANNEL_USER_MAP = new ConcurrentHashMap<>();
        // 记录用户Id与SocketChannel
        static final Map<String, SocketChannel> USER_CHANNEL_MAP = new ConcurrentHashMap<>();

        /**
         * 加入群聊
         */
        static void join(SocketChannel socketChannel) {
            // 有人加入就给他分配一个id
            String userId = "用户" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
            send(socketChannel, "您的id为：" + userId + "\n\r");

            for (SocketChannel channel : CHANNEL_USER_MAP.keySet()) {
                send(channel, userId + " 加入了群聊" + "\n\r");
            }

            // 将当前用户加入到map中
            CHANNEL_USER_MAP.put(socketChannel, userId);
            USER_CHANNEL_MAP.put(userId.replaceAll("用户", ""), socketChannel);
        }

        /**
         * 退出群聊
         */
        static void quit(SocketChannel socketChannel) {
            String userId = CHANNEL_USER_MAP.get(socketChannel);
            send(socketChannel, "您退出了群聊" + "\n\r");
            CHANNEL_USER_MAP.remove(socketChannel);

            for (SocketChannel channel : CHANNEL_USER_MAP.keySet()) {
                if (channel != socketChannel) {
                    send(channel, userId + " 退出了群聊" + "\n\r");
                }
            }
        }

        /**
         * 单聊
         */
        public static void singleChat(SocketChannel socketChannel, String content) {
            String userId = CHANNEL_USER_MAP.get(socketChannel);

            String[] contentArr = content.split(":");
            String toUserId = contentArr[0];
            String toContent = contentArr[1];
            SocketChannel toSocketChannel = USER_CHANNEL_MAP.get(toUserId); // 获取收消息方SocketChannel
            send(toSocketChannel, userId + ": " + toContent + "\n\r");
        }

        /**
         * 扩散说话的内容
         */
        public static void propagate(SocketChannel socketChannel, String content) {
            String userId = CHANNEL_USER_MAP.get(socketChannel);
            for (SocketChannel channel : CHANNEL_USER_MAP.keySet()) {
                if (channel != socketChannel) {
                    send(channel, userId + ": " + content + "\n\r");
                }
            }
        }

        /**
         * 发送消息
         */
        static void send(SocketChannel socketChannel, String msg) {
            try {
                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                writeBuffer.put(msg.getBytes());
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

