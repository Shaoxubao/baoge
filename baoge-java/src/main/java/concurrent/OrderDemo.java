/**
 * Copyright 2016-2018 www.fangdd.com All Rights Reserved.
 * Author: Shao Xu Bao <shaoxubao-sz@fangdd.com>
 * Date:   2018/6/24
 */

package concurrent;

public class OrderDemo {

    private static int a = 0;
    private static boolean flag = false;

    public synchronized void write() {
        a++;
        System.out.println(a);
        flag = true;
    }

    public synchronized void read() {
        if (flag) {
            int i = a + 1;
            System.out.println(i);
            System.exit(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final OrderDemo orderDemo = new OrderDemo();

        Thread threadA = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("write thread start:");
                    orderDemo.write();
                }
            }
        };

        Thread threadB = new Thread() {
          @Override
          public void run() {
              while (true) {
                  System.out.println("read thread start:");
                  orderDemo.read();
              }
          }
        };

        threadA.start();
        threadB.start();
        while(Thread.activeCount()>1) { // 保证前面的线程都执行完
            Thread.yield();
        }

    }

}
