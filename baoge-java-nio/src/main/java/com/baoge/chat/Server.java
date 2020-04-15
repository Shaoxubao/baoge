package com.baoge.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/1/14 14:26
 */
public class Server {

    private Selector selector;
    // 人数统计、昵称和主机地址记录
    private Map<String, String> users = new HashMap<>();
    ByteBuffer buffer = ByteBuffer.allocate(2048);
    public Server(int port) throws IOException {
        // 开启服务端通道
        ServerSocketChannel server = ServerSocketChannel.open();
        // 监听端口
        server.bind(new InetSocketAddress(port));
        // 切换非阻塞模式
        server.configureBlocking(false);
        // 开启选择器
        selector = Selector.open();
        // 选择器注册到服务端通道上
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动...");
    }
    /**
     * 通过监听选择键来监听客户端连接
     * @throws IOException
     */
    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 移除已处理的选择键
                iterator.remove();
                // 处理选择键
                handle(key);
            }
            // 清空选择键
            selector.selectedKeys().clear();
        }
    }
    /**
     * 处理选择键
     * @param key
     * @throws IOException
     */
    private void handle(SelectionKey key) throws IOException {
        ServerSocketChannel server;
        SocketChannel client;
        if (key.isAcceptable()) {
            // 获取key对应的通道
            server = (ServerSocketChannel) key.channel();
            // 获取服务端连接
            client = server.accept();
            client.configureBlocking(false);
            // 注册到选择器，指定行为是"读"
            client.register(selector, SelectionKey.OP_READ);
            System.out.println("接收到来自 " + client.getRemoteAddress() + " 的新连接！");
            boardMsg("当前在线人数：" + users.size());
            write("\n欢迎来到本聊天室，请输入昵称：", client);
            key.interestOps(SelectionKey.OP_ACCEPT);
        } else if (key.isReadable()) {
            client = (SocketChannel) key.channel();
            try {
                String[] msg = rec(client).split("###");
                if (msg.length == 1) {      // 设置昵称
                    if (users.containsValue(msg[0])) {
                        write("昵称重复，请重新输入！", client);
                    } else {
                        users.put(client.getRemoteAddress().toString(), msg[0]);
                        write("hello " + msg[0], client);
                    }
                } else if (msg.length == 2) {
                    System.out.println(client.getRemoteAddress() + " named " + msg[0] + " said to all: " + msg[1]);
                    boardMsg(msg[0] + "说：" + msg[1]);
                } else if (msg.length == 3) {
                    System.out.println(client.getRemoteAddress() + " named " + msg[0] + " said to " + msg[2] + ": " + msg[1]);
                    p2pChat(msg[0] + "说：" + msg[1], msg[2], client);
                }
            } catch (Exception e) {
                String address = client.getRemoteAddress().toString();
                System.out.println(address + " 断开了连接！");
                client.close();
                String name = users.get(address);
                users.remove(address);
                boardMsg("用户 " + name + " 离开了！当前在线人数：" + users.size());
            }
        }
    }
    /**
     * 读消息
     * @param channel
     * @return
     * @throws IOException
     */
    private String rec(SocketChannel channel) throws IOException {
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
    private void write(String msg, SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put(msg.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        channel.write(buffer);
    }
    /**
     * 分发消息给全部客户端，群聊
     * @param msg
     * @throws IOException
     */
    private void boardMsg(String msg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel target = key.channel();
            if (target.isOpen() && target instanceof SocketChannel) {
                write(msg, (SocketChannel) target);
            }
        }
    }
    /**
     * 发送消息给指定客户端，单聊
     * @param msg
     * @param targetName
     * @param source
     * @throws IOException
     */
    private void p2pChat(String msg, String targetName, SocketChannel source) throws IOException {
        boolean flag = false;
        for (SelectionKey key:selector.keys()) {
            Channel target = key.channel();
            if (target.isOpen() && target instanceof SocketChannel) {
                SocketChannel tar = (SocketChannel) target;
                String name = users.get(tar.getRemoteAddress().toString());
                if (name.equals(targetName)) {
                    write(msg, (SocketChannel) target);
                    write(msg, source);
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            write("找不到该用户!", source);
        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(7777);
        server.listen();
    }

}
