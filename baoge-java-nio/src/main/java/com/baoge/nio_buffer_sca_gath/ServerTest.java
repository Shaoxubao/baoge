package com.baoge.nio_buffer_sca_gath;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author shaoxubao
 * @Date 2020/1/6 9:45
 *
 * 关于Buffer的Scattering和Gathering
 * Scattering:将一个Channel中数据读取到多个Buffer，Gathering相反
 *
 * 通过telnet localhost 8890测试，或运行ClientTest
 */
public class ServerTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.bind(inetSocketAddress);
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        while (true) {
            long readLength = 0;
            while (readLength < messageLength) {
                long r = socketChannel.read(buffers);
                readLength += r;
            }

            System.out.println("readLength: " + readLength);

            Arrays.asList(buffers).stream()
                    .map(buffer -> "position: " + buffer.position() + ", limit: " + buffer.limit())
            .forEach(System.out::println);

            // flip()
            Arrays.asList(buffers).forEach(buffer -> {
                buffer.flip();
            });

            long writtenLength = 0;
            while (writtenLength < messageLength) {
                long w = socketChannel.write(buffers);
                writtenLength += w;
            }

            // clear()
            Arrays.asList(buffers).forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("end " + "readLength: " + readLength + ", writtenLength: " + writtenLength + ", messageLength: " + messageLength);
        }
    }
}
