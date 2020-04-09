package com.baoge.test.statictest;

/**
 * @Author shaoxubao
 * @Date 2020/4/9 14:26
 */
public class StaticTest {

    public static void main(String[] args) {

        System.out.println("main start:");

        Packet packet1 = Packet.INSTANCE;
        System.out.println(packet1);
        Packet packet2 = Packet.INSTANCE;
        System.out.println(packet2);

        Packet.INSTANCE = new Packet(); // 修改INSTANCE变量（非final定义时可以修改）

        Packet packet3 = Packet.INSTANCE;
        System.out.println(packet3);
    }

}
