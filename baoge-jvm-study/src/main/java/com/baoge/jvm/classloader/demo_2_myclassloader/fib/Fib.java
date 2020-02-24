package com.baoge.jvm.classloader.demo_2_myclassloader.fib;

/**
 * @Author shaoxubao
 * @Date 2020/2/24 10:44
 *
 * 斐波那契:
 * 1 1 2 3 5 ...
 */
public class Fib {

    public static int fib(int num) {
        return num < 2 ? num : fib(num - 2) + fib(num - 1);
    }

}
