package concurrent.thread.add_dec_j;

/**
 * 设计4个线程。，其中两个线程每次对j增加1，另外两个线程对j每次减1
 */
public class MyData {

    private int j = 0;

    public synchronized void add() {
        j++;
        System.out.println("线程"+Thread.currentThread().getName()+"j为：" + j);
    }

    public synchronized void dec() {
        j--;
        System.out.println("线程"+Thread.currentThread().getName()+"j为：" + j);
    }

    public int getData() {
        return j;
    }

}
