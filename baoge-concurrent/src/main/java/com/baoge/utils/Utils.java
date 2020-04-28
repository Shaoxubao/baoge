package com.baoge.utils;

import java.util.Random;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/28
 */
public class Utils {

    public static int[] buildRandomIntArray(int length) {
        int[] result = new int[length];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000);
        }

        return result;
    }

}
