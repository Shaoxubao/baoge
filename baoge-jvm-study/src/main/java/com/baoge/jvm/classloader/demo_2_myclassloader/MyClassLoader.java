package com.baoge.jvm.classloader.demo_2_myclassloader;

import java.io.FileInputStream;

/**
 * @Author shaoxubao
 * @Date 2020/2/24 10:35
 *
 * 编写一个自己的类加载器
 *
 */
public class MyClassLoader extends ClassLoader {

    private String classPath;  // 保存的地址

    public MyClassLoader (String classPath) {
        this.classPath = classPath;
    }

    /**
     * 读取class文件
     */
    private byte[] loadByte(String className) throws Exception {
        className = className.replace(".", "\\");
        String inPath = classPath + "/" + className + ".class";
        FileInputStream fis = new FileInputStream(inPath);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    /**
     * 重写findClass方法，让加载的时候调用findClass方法
     */
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            // 将字节码载入内存
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
