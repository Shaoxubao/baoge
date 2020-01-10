package com.baoge.reactor_nio.reactore_master_slave_thread;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @Author shaoxubao
 * @Date 2020/1/10 9:41
 *
 * 业务线程
 * 该handler的功能就是在收到的请求信息，后面加上 hello,服务器收到了你的信息，然后返回给客户端
 */
public class Handler implements Runnable {

    private static final byte[] b = "hello,服务器收到了你的信息。".getBytes(); // 服务端给客户端的响应

    private SocketChannel sc;
    private ByteBuffer reqBuffer;
    private SubReactorThread parent;

    public Handler(SocketChannel sc, ByteBuffer reqBuffer,
                   SubReactorThread parent) {
        super();
        this.sc = sc;
        this.reqBuffer = reqBuffer;
        this.parent = parent;
    }

    public void run() {
        System.out.println("业务在handler中开始执行。。。");
        try {
            // 业务处理
            // 打印收到信息
            reqBuffer.flip();
            byte[] response = new byte[reqBuffer.remaining()];
            reqBuffer.get(response);
            System.out.println("业务处理收到结果信息：" + new String(response, "utf-8"));

            // 向客户端发送消息
            reqBuffer.clear();
            reqBuffer.put(b);
            parent.register(new NioTask(sc, SelectionKey.OP_WRITE, reqBuffer));
            System.out.println("业务在handler中执行结束。。。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
