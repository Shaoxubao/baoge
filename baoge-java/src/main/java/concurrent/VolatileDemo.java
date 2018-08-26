/**
 * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
 * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
 * Date:   2018/6/23
 */

package concurrent;

public class VolatileDemo {

    private static volatile VolatileDemo instance;

    public static VolatileDemo getInstance() {
        if (instance == null) {
            instance = new VolatileDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileDemo.getInstance();
    }

}
