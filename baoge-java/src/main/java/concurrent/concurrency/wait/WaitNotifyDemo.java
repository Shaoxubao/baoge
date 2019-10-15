package concurrent.concurrency.wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shaoxubao
 * @Date 2019/10/15 17:29
 */
public class WaitNotifyDemo {
    private synchronized void forNotify() {
        System.out.println("for notify begin");
        notify();
        System.out.println("for notify finish");
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
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(waitNotifyDemo::forWaitA);
        executorService.execute(waitNotifyDemo::forWaitB);
        executorService.execute(waitNotifyDemo::forNotify);
        /* 输出：
        for wait A begin
        for wait B begin
        for notify begin
        for notify finish
        for wait A finish
         */
    }

}
