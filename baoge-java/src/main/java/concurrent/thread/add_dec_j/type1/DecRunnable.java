package concurrent.thread.add_dec_j.type1;

import concurrent.thread.add_dec_j.MyData;

public class DecRunnable implements Runnable {

    MyData data;

    public DecRunnable(MyData data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.dec();
    }

}
