package com.baoge.nio_buffer_sca_gath;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author shaoxubao
 * @Date 2020/1/6 10:23
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        String newData = "helloword";
//        String newData = "hello";

        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        writeBuffer.clear();
        writeBuffer.put(newData.getBytes());

        writeBuffer.flip();

        while (writeBuffer.hasRemaining()) {
            socketChannel.write(writeBuffer);
        }

        int bytesRead = socketChannel.read(readBuffer);
        if (bytesRead > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[bytesRead];
            readBuffer.get(bytes, 0, bytesRead);
            String str = new String(bytes);
            System.out.println(str);
            readBuffer.clear();
        }

        socketChannel.close();
    }
}
