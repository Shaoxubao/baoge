package concurrent.thread.jioujiaoti;

/**
 * synchronized实现奇偶交替
 */
public class ThreadPrintDemo2 {

    public synchronized void printOdd() {
        for (int i = 1; i <= 100; i += 2) {
            System.out.println("奇数：" + i);
            this.notify();

            try {
                this.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void printEven() {
        for (int i = 0; i <= 100; i += 2) {
            System.out.println("偶数：" + i);
            this.notify();

            try {
                this.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final ThreadPrintDemo2 demo2 = new ThreadPrintDemo2();

        Thread t1 = new Thread(demo2::printEven);
        Thread t2 = new Thread(demo2::printOdd);

        t1.start();
        t2.start();

    }

}
