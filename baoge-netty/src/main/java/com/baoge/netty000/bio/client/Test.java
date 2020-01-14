package com.baoge.netty000.bio.client;

import com.baoge.netty000.bio.server.BioServer;

/**
 * @Author shaoxubao
 * @Date 2019/11/11 11:28
 */
public class Test {

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BioClient.runClient();
    }

}
