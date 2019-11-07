package com.baoge.netty001.bio.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 16:03
 */
public class BioServer extends Thread {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9090));
            System.out.println("baoge-demo-netty bio server start done.");
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("utf-8"));
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
