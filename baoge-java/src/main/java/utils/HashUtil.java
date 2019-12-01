package utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/1
 */
public class HashUtil {

    /**
     * 计算对象hashCode
     */
    public static void calculateHashCode(Object object) {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);

            Unsafe unsafe = (Unsafe) field.get(null);

            long hashCode = 0;
            for (int index = 7; index > 0; index--) {
                // 取Mark Word中每一个Byte进行计算
                hashCode |= (unsafe.getByte(object, index) & 0xFF) << ((index - 1) * 8);
            }

            String result = Long.toHexString(hashCode);

            System.out.println("the hashCode is : 0x" + result);
        } catch (Exception e) {

        }

    }

}
