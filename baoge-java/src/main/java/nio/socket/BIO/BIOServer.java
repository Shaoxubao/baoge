package nio.socket.BIO;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/10/26
 */
public class BIOServer {
    final static int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("server start");
            Socket socket = null;
            HandlerExecutorPool executorPool = new HandlerExecutorPool(25,50, 1000,
                    TimeUnit.SECONDS, new LinkedBlockingQueue());
            while (true) {
                socket = server.accept();
                System.out.println("有新的连接，当前线程数量：" + executorPool.getActiveCount());
                executorPool.execute(new ServerHandler(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            server = null;
        }



    }

}
