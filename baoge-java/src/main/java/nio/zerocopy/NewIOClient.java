package nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author shaoxubao
 * @Date 2019/8/12 10:56
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8888));
        socketChannel.configureBlocking(true);

        String fileName = "E:/webant.7z"; // 953.4 MB

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.nanoTime();

        // 一行代码实现0拷贝，将文件channel中的内容直接写到SocketChannel中
        long transferCount = fileChannel.transferTo(0,fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + transferCount + ", 耗时 ：" + (System.nanoTime() - startTime) / 1000000);

        fileChannel.close();
        socketChannel.close();
    }

}
