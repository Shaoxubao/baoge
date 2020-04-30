package com.baoge;

/**
 * maven 打包
 * -javaagent:E:\workspace\baoge\baoge-java-agent\my-agent\target\my-agent-1.0-SNAPSHOT.jar
 */
public class TimeTest {
	
	public static void main(String[] args) {
//        sayHello();
        sayHello2("hello world222222222");
    }

    public static void sayHello() {
        try {
            Thread.sleep(2000);
            System.out.println("hello world!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sayHello2(String hello) {
        try {
            Thread.sleep(1000);
            System.out.println(hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
