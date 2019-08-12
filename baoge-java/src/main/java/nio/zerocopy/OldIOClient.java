package nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Author shaoxubao
 * @Date 2019/8/12 10:51
 */
public class OldIOClient {
    public static void main(String[] args) throws  Exception {

        Socket socket = new Socket("localhost",8888);

        String fileName = "E:/webant.7z"; // 大小953.4 MB
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int readCount = 0;
        long total = 0;

        long startTime = System.nanoTime();

        while((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer,0,readCount);
        }

        System.out.println("发送总字节数：" + total + ", 耗时 ：" + (System.nanoTime() - startTime)/1000000);

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
