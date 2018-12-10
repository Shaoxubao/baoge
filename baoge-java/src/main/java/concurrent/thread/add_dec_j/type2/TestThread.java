package concurrent.thread.add_dec_j.type2;

import concurrent.thread.add_dec_j.MyData;

/**
 * 与第一种方法TestOne的区别在于第二种方法巧妙的用了内部类共享外部类数据的思想，即把要共享的数据变得全局变量，
 * 这样就保证了操作的是同一份数据。同时内部类的方式使代码更加简洁。但是不如第一种解法条理那么清楚。
 */
public class TestThread {

    public static void main(String[] args) {
        final MyData data = new MyData();

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.add();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    data.dec();
                }
            }).start();
        }

    }

}
