package com.baoge.jvm.classloader.demo_1;

import com.baoge.jvm.classloader.MyTest16_MyClassLoader;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/26
 *
 * 关于命名空间重要说明：
 * 1、子类加载器所加载的类能够访问父类加载所加载的类
 * 2、父类加载器所加载的类无法访问到子类加载器所加载的类
 *
 */
public class MyTest17_1 {

    public static void main(String[] args) throws Exception {
        MyTest16_MyClassLoader loader1 = new MyTest16_MyClassLoader("loader1");

        // 将classpath下的MySample和MyCat文件移到下面path中（MySample和MyCat文件一直存在），删除classpath下的MySample和MyCat文件
        // 若只删除classpath下的MyCat文件，则MySample由系统类加载器加载，在加载MyCat的时候报错，系统类加载器加载找不到classpath下的MyCat文件
        // 若只删除classpath下的MySample文件，则MySample由自定义类加载器MyTest16_MyClassLoader加载，
        // 在实例化MySample类构造方法中，自定义类加载器MyTest16_MyClassLoader会加载MyCat，会委托父类加载器即
        // 系统类加载器加载，此时classpath下的MyCat文件是存在的，所以MyCat由系统类加载器加载成功
        loader1.setPath("C:\\Users\\Qing\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.baoge.jvm.classloader.demo_1.MySample");
        System.out.println("class:" + clazz.hashCode());

        // 如果注释此行，则不会实例化MySample类，只会将MySample类加载到内存，不会加载MyCat
        Object object = clazz.newInstance();

        // MyCat和MySample类中还有猫腻，详情请点开类查看

    }

}
