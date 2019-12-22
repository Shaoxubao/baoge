package com.baoge.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/22
 */
public class MyTest16 extends ClassLoader {

    private String classLoaderName;

    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        super(); // 将系统类加载器当作该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent); // 显式指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String className) {
        byte[] data = loadClassData(className);

        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream out = null;

        try {
            this.classLoaderName = name.replace(".", "/");
            in = new FileInputStream(new File(name + this.fileExtension));

            out = new ByteArrayOutputStream();

            int ch = 0;

            while (-1 != (ch = in.read())) {
                out.write(ch);
            }

            data = out.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.baoge.jvm.classloader.MyTest1");

        Object object = clazz.newInstance();

        System.out.println(object);
    }

    @Override
    public String toString() {
        return "MyTest16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }


    public static void main(String[] args) throws Exception {
        MyTest16 myTest16 = new MyTest16("loader1");
        test(myTest16);
    }

}
