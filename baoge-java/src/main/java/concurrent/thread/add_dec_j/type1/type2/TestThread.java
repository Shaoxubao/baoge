package concurrent.thread.add_dec_j.type1.type2;

import concurrent.thread.add_dec_j.MyData;

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
