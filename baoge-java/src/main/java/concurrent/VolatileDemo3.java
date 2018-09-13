package concurrent;

public class VolatileDemo3 {

    private volatile boolean isExit;

    public void swapValue() {
        isExit = !isExit;
    }

    public void stop() {
        if (isExit == !isExit) {
            System.exit(0);
        }
        System.out.println("stop fail!");
    }


    public static void main(String[] args) throws InterruptedException {
        final VolatileDemo3 volatileDemo = new VolatileDemo3();
        Thread mainThread = new Thread() {
            @Override
            public void run() {
                System.out.println("main thread start:");
                while (true) {
                    volatileDemo.stop();
                }
            }
        };
        mainThread.start();

        Thread swapThread = new Thread() {
            @Override
            public void run() {
                System.out.println("swap thread start:");
                while (true) {
                    volatileDemo.swapValue();
                }
            }
        };
        swapThread.start();

        Thread.sleep(10000);

    }
}
