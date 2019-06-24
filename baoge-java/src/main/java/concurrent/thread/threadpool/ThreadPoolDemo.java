package concurrent.thread.threadpool;

/**
 * @Author shaoxubao
 * @Date 2019/6/24 10:42
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        ThreadPoolService.execute(new Runnable() {
            @Override
            public void run() {
                printA();
                System.out.println("=====================");
                printB();
            }
        });

        ThreadPoolService.shutDown();
    }

    private static void printA() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i + "A");
        }
    }

    private static void printB() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i + "B");
        }
    }

}
