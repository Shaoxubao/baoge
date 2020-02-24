package com.baoge.jvm.classloader.demo_2_myclassloader;

import java.lang.reflect.Method;

/**
 * @Author shaoxubao
 * @Date 2020/2/24 10:46
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        // 初始化类加载器
//        MyClassLoader myClassLoader = new MyClassLoader("E:\\workspace\\baoge\\baoge-jvm-study\\target\\classes");

        // 加载Fib类,笔者class文件包名为com.baoge.jvm.classloader.demo_2_myclassloader.fib
//        Class myClass = myClassLoader.loadClass("com.baoge.jvm.classloader.demo_2_myclassloader.fib.Fib");

        // 删除E:\\workspace\\baoge\\baoge-jvm-study\\target\\classes下的Fib.class，复制放到桌面，将使用自定义类加载器
        MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\lenovo\\Desktop");
        Class myClass = myClassLoader.loadClass("com.baoge.jvm.classloader.demo_2_myclassloader.fib.Fib");

        // 获取加载类的实例
        Object object = myClass.newInstance();

        System.out.println("the classLoader is :" + object.getClass().getClassLoader());

        // 获取该类一个名为fib,且参数为int的方法
        Method method = myClass.getMethod("fib", int.class);

        // 执行这个方法
        Object result = method.invoke(object, 5);

        // 打印结果
        System.out.println(result);
    }

}
