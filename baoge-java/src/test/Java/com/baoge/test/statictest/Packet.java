package com.baoge.test.statictest;

/**
 * @Author shaoxubao
 * @Date 2020/4/9 14:28
 */
public class Packet {

    public static Packet INSTANCE = new Packet();
//    public static final Packet INSTANCE = new Packet();
//    public static Packet INSTANCE = null;

    static {
        System.out.println("packet init");

//        if (INSTANCE == null) {
//            INSTANCE = new Packet();
//        }

    }

}
