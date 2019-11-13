package com.baoge.NIO.client;

import com.baoge.NIO.server.Server;

import java.util.Scanner;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 19:03
 */
public class Test {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        // 运行服务器
        Server.start();
        // 避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        // 运行客户端
        Client.start();
        // 输入一个表达式，如：1+2
        while (Client.sendMsg(new Scanner(System.in).nextLine()));
    }

}
