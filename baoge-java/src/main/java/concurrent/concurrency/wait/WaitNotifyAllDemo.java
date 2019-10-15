package concurrent.concurrency.wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shaoxubao
 * @Date 2019/10/15 17:27
 */
public class WaitNotifyAllDemo {

    private synchronized void forNotifyAll() {
        System.out.println("for notifyAll begin");
        notifyAll();
        System.out.println("for notifyAll finish");
    }

    private synchronized void forWaitA() {
        System.out.println("for wait A begin");
        try {
            wait();
        } catch (InterruptedException ignored) {
        }
        System.out.println("for wait A finish");
    }

    private synchronized void forWaitB() {
        System.out.println("for wait B begin");
        try {
            wait();
        } catch (InterruptedException ignored) {
        }
        System.out.println("for wait B finish");
    }

    public static void main(String[] args) {
        WaitNotifyAllDemo waitNotifyDemo = new WaitNotifyAllDemo();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(waitNotifyDemo::forWaitA);
        executorService.execute(waitNotifyDemo::forWaitB);
        executorService.execute(waitNotifyDemo::forNotifyAll);
        /* 输出：
        for wait A begin
        for wait B begin
        for notifyAll begin
        for notifyAll finish
        for wait B finish
        for wait A finish
        */
    }

}
