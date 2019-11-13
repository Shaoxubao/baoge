package nio.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author shaoxubao
 * @Date 2019/8/12 10:49
 */
public class OldIOServer {
    public static void main(String[] args) throws Exception {
        // 创建一个ServerSocket并监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            // 阻塞方法，获得连接的socket对象
            Socket socket = serverSocket.accept();
            // 通过装饰器模式获取DataInputStream
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            int totalCount = 0;
            // 读取数据
            try {
                byte[] buffer = new byte[4096];

                int read = 0;
                while ((read = dataInputStream.read(buffer)) > 0) {
                    totalCount += read;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("服务端接受字节数:" + totalCount);
        }
    }
}
