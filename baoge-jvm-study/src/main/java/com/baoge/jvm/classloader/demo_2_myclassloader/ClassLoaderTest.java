package com.baoge.jvm.classloader.demo_2_myclassloader;

import java.lang.reflect.Method;

/**
 * @Author shaoxubao
 * @Date 2020/2/24 10:46
 *
 * 分析下,Fib类的加载过程,初始化自定义类加载器后,loadClass方法肯定将其委派到双亲Application ClassLoader,
 * 而Application ClassLoader又将其委派到Extension ClassLoader,继而委派到Bootstrap ClassLoader.
 * 但是Bootstrap ClassLoader发现Fib并不在自己的加载能力范围内,于是移向Extension ClassLoader,同理Extension ClassLoader只能加载/ext中的class,
 * 继而让给Application ClassLoader,而Application ClassLoader只加载classpath中的类,于是又回到我们自定义的MyClassLoader,
 * 幸好我们重写了findClass方法进而执行了加载,否在findClass抛出找不到类的异常.至此Fib类加载完成.
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
