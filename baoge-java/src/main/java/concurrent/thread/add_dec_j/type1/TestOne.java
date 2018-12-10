package concurrent.thread.add_dec_j.type1;

import concurrent.thread.add_dec_j.MyData;

public class TestOne {
    public static void main(String[] args) {

        MyData data = new MyData();

        Runnable addRunnable = new AddRunnable(data);
        Runnable decRunnable = new DecRunnable(data);

        for (int i = 0; i < 2; i++) {
            new Thread(addRunnable).start();
            new Thread(decRunnable).start();
        }

    }
}
