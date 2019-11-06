package com.baoge.AIO.client;

import com.baoge.AIO.server.Server;

import java.util.Scanner;

/**
 * @Author shaoxubao
 * @Date 2019/11/6 19:39
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
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);
        while(Client.sendMsg(scanner.nextLine()));
    }

}
