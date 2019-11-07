package com.baoge.netty001.bio.client;

import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 16:15
 */
public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9090);
            System.out.println("baoge-demo-netty bio client start done. ");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
