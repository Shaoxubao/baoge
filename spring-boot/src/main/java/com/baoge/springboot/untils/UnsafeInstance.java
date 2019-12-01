package com.baoge.springboot.untils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/1
 */
public class UnsafeInstance {

    public static Unsafe reflectGetUnsafe() {

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
