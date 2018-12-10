package concurrent.thread.add_dec_j.type1;

import concurrent.thread.add_dec_j.MyData;

public class AddRunnable implements Runnable {

    MyData myData;

    public AddRunnable(MyData myData) {
        this.myData = myData;
    }


    @Override
    public void run() {
        myData.add();
    }
}
