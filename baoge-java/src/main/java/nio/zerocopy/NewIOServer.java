package nio.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author shaoxubao
 * @Date 2020/1/13 10:09
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(8888);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);  // 若连接关闭了处于超时状态，设置true时可以绑定到这个超时状态ServerSocket
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int totalCount = 0;
            int readCount = 0;
            while (-1 != readCount) {
                readCount = socketChannel.read(byteBuffer);

                totalCount += readCount;

                // position归位为0
                byteBuffer.rewind();
            }

            System.out.println("接收到总字节数：" + totalCount);
        }
    }

}
