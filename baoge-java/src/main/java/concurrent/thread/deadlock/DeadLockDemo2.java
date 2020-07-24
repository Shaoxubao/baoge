package concurrent.thread.deadlock;

import java.util.Arrays;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 11:02
 *
 * 死锁
 */
public class DeadLockDemo2 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list1) {
                    for (Integer item : list1) {
                        System.out.println(item);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (list2) {
                        for (Integer item : list2) {
                            System.out.println(item);
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list2) {
                    for (Integer item : list2) {
                        System.out.println(item);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (list1) {
                        for (Integer item : list1) {
                            System.out.println(item);
                        }
                    }
                }
            }
        }).start();
    }
}
