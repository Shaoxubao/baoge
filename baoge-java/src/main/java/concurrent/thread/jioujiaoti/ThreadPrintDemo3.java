package concurrent.thread.jioujiaoti;

/**
 * volatile实现奇偶交替
 */
public class ThreadPrintDemo3 {

    static volatile int num = 0;
    static volatile boolean flag = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (; 100 > num; ) {
                if (!flag && (num == 0 || ++num % 2 == 0)) {

                    try {
                        Thread.sleep(100); // 防止打印速度过快导致混乱
                    } catch (InterruptedException e) {
                        // NO
                    }

                    System.out.println("偶数：" + num);
                    flag = true;
                }
            }
        }
        );

        Thread t2 = new Thread(() -> {
            for (; 100 > num; ) {
                if (flag && (++num % 2 != 0)) {

                    try {
                        Thread.sleep(100);// 防止打印速度过快导致混乱
                    } catch (InterruptedException e) {
                        // NO
                    }

                    System.out.println("奇数：" + num);
                    flag = false;
                }
            }
        }
        );

        t1.start();
        t2.start();

    }

}
