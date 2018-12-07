package concurrent.thread;

/**
 * 使用三个线程轮流打印ABC字符串3次
 * 仔细理一下：
 （1）首先三个线程启动后，一定是A线程先打印。如果是其他线程先启动，则必须等待，线程间的通信，我们用共享变量来解决。（本质是通过共享内存）
 （2）A运行的时候，B和C都在等待
 （3）B运行的时候，A和C都在等待
 （4）C运行的时候，A和B都在等待
 （5）A运行结束通知B运行
 （6）B运行结束通知C运行
 （7）C运行结束通知A运行
 （8）同时，如果要控制几轮打印，则需要在运行时控制循环次数，因为C线程是每一轮的结束标志，循环次数的加和要在C线程里面做。
  定义了共享的监视器对象，计数器，共享变量，然后定义了三个方法分别负责打印A,B,C，功能的实现主要用了synchronized + 监视器的wait，notifyAll方法。
 */
public class PrintABC {
    final Object monitor = new Object();
    volatile int count = 1; // 轮次计数，从1开始，为了保证可见性，这里需要用volatile修饰
    String id = "A"; // 贡献的
    int printCount;
    public PrintABC(int printCount) {
        this.printCount = printCount;
    }

    public void printA() throws InterruptedException {
        while (count < printCount) {
            synchronized (monitor) {
                while (!id.equals("A")) {
                    monitor.wait();
                }
                System.out.println(Thread.currentThread().getName() + "打印： " + id);
                id = "B";
                monitor.notifyAll();
            }
        }
    }

    public void printB() throws InterruptedException {
        while (count < printCount) {
            synchronized (monitor) {
                while (!id.equals("B")) {
                    monitor.wait();
                }
                System.out.println(Thread.currentThread().getName() + "打印： " + id);
                id = "C";
                monitor.notifyAll();
            }
        }
    }

    public void printC() throws InterruptedException {
        while (count < printCount + 1) { // 最后一次终结线程，需要多加一次
            synchronized (monitor) {
                while (!id.equals("C")) {
                    monitor.wait();
                }
                System.out.println(Thread.currentThread().getName() + "打印： " + id + "\n");
                id = "A";
                count = count + 1;
                monitor.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        final PrintABC printABC = new PrintABC(3);

        Thread t1 = new Thread(() -> {
            try {
                printABC.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("A线程");

        Thread t2 = new Thread(() -> {
            try {
                printABC.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.setName("B线程");

        Thread t3 = new Thread(() -> {
            try {
                printABC.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.setName("C线程");

        t1.start();
        t2.start();
        t3.start();

    }

}
