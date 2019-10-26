package nio.socket.BIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/10/24
 *
 * 此例测试Tomcat7和Tomcat8性能区别，需手动依次先启动tomcat，jvisualvm工具监测
 *
 */
public class SocketClientTest {

    static int stateCount = 100;

    static CountDownLatch cdt = new CountDownLatch(stateCount);

    public static void main(String[] args) {
        for (int i = 0; i < stateCount; i++) {
            ClientThread ct = new ClientThread();
            new Thread(ct).start();
            cdt.countDown();
        }
    }

    static class ClientThread implements Runnable {

        // 连接tomcat 8080端口，模拟高并发测试
        @Override
        public void run() {
            try {
                cdt.await();

                BufferedReader in = null;
                PrintWriter out = null;

                // ServerSocket 服务器端，Socket 客户端
                Socket socket = new Socket("localhost", 8080);
                System.out.println(Thread.currentThread().getName() + "working...");

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Client request:" + Thread.currentThread().getName());

                String response = in.readLine();
                System.out.println("Client:" + response);

                Thread.sleep(60 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
