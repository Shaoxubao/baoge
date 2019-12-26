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
public class MyTest16_MyClassLoader extends ClassLoader {

    private String classLoaderName;

    private String path;

    private final String fileExtension = ".class";

    public MyTest16_MyClassLoader(String classLoaderName) {
        super(); // 将系统类加载器当作该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyTest16_MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent); // 显式指定该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) {
        System.out.println("findClass invoked:" + className);
        System.out.println("class loader name:" + this.classLoaderName);

        byte[] data = loadClassData(className);

        return this.defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream out = null;

        try {
            className = className.replace(".", "\\");

            in = new FileInputStream(new File(this.path + className + this.fileExtension));

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
        System.out.println(object.getClass().getClassLoader());
    }

    @Override
    public String toString() {
        return "MyTest16_MyClassLoader{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }


    public static void main(String[] args) throws Exception {
        /**
        MyTest16_MyClassLoader loader1 = new MyTest16_MyClassLoader("loader1");
//        myTest16.setPath("workspace\\baoge\\baoge-jvm-study\\target\\classes");

        // 删除项目classes下的MyTest1，加载桌面路径下MyTest1，会调用自定义的类加载器
        loader1.setPath("C:\\Users\\Qing\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.baoge.jvm.classloader.MyTest1");
        System.out.println("class:" + clazz.hashCode());

        Object object = clazz.newInstance();

        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());


        System.out.println("========================");

//        MyTest16_MyClassLoader loader2 = new MyTest16_MyClassLoader("loader2");
        MyTest16_MyClassLoader loader2 = new MyTest16_MyClassLoader(loader1,"loader2");
        loader2.setPath("C:\\Users\\Qing\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.baoge.jvm.classloader.MyTest1");
        System.out.println("class2:" + clazz2.hashCode());

        Object object2 = clazz2.newInstance();

        System.out.println(object2);
        System.out.println(object2.getClass().getClassLoader());

        System.out.println("========================");

        MyTest16_MyClassLoader loader3 = new MyTest16_MyClassLoader("loader3");
        loader3.setPath("C:\\Users\\Qing\\Desktop\\");

        Class<?> clazz3 = loader3.loadClass("com.baoge.jvm.classloader.MyTest1");
        System.out.println("class2:" + clazz3.hashCode());

        Object object3 = clazz3.newInstance();

        System.out.println(object3);
        System.out.println(object3.getClass().getClassLoader());


//        test(myTest16);
*/

        System.out.println("============以下演示类的卸载============");

        MyTest16_MyClassLoader loader1 = new MyTest16_MyClassLoader("loader1");
//        myTest16.setPath("workspace\\baoge\\baoge-jvm-study\\target\\classes");
        loader1.setPath("C:\\Users\\Qing\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.baoge.jvm.classloader.MyTest1");
        System.out.println("class:" + clazz.hashCode());

        Object object = clazz.newInstance();
        System.out.println(object);

        loader1 = null;
        clazz = null;
        object = null;

        System.gc();

        Thread.sleep(100000); // jvisualvm 工具观察类卸载情况

        System.out.println("==============");

        loader1 = new MyTest16_MyClassLoader("loader1");
        loader1.setPath("C:\\Users\\Qing\\Desktop\\");

        clazz = loader1.loadClass("com.baoge.jvm.classloader.MyTest1");
        System.out.println("class:" + clazz.hashCode());

        object = clazz.newInstance();
        System.out.println(object);


    }

}
