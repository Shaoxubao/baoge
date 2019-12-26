package com.baoge.jvm.classloader.demo_1;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/26
 */
public class MyCat {

    public MyCat() {
        System.out.println("MyCat is loaded by:" + this.getClass().getClassLoader());

        // 此处定义输出MySample.class，运行此处，先注释MySample类中输出MyCat.class，会报错“NoClassDefFoundError”：
        // 只删除classpath下的MySample文件,则MySample由自定义类加载器加载，MyCat由委托机制父加载器
        // 系统类加载器加载，由类加载命名空间知，父类加载器是看不到子类加载器加载的类的，而子类加载器
        // 是可以看到父类加载器加载的类，所以加载MyCat的加载器找不到MySample而报错。
//        System.out.println("from MyCat:" + MySample.class);
    }

}
