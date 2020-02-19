package concurrent.thread.shunxuzhixing;

import java.util.concurrent.CountDownLatch;

/**
 * @Author shaoxubao
 * @Date 2020/2/19 15:32
 *
 *  使用线程的 CuDownLatch(倒计数) 方法使线程按顺序执行
 *
 *  CountDownLatch: 位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
 *
 *  应用场景: 比如有一个任务C，它要等待其他任务A,B执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 */
public class Demo006 {

    /**
     * 用于判断线程一是否执行，倒计时设置为1，执行后减1
     */
    private static CountDownLatch c1 = new CountDownLatch(1);

    /**
     * 用于判断线程二是否执行，倒计时设置为1，执行后减1
     */
    private static CountDownLatch c2 = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理规划新需求");

                // 对c1倒计时-1
                c1.countDown();
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 等待c1倒计时，计时为0则往下运行
                    c1.await();

                    System.out.println("开发人员开发新需求功能");

                    // 对c2倒计时-1
                    c2.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 等待c2倒计时，计时为0则往下运行
                    c2.await();

                    System.out.println("测试人员测试新功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("开发人员来上班了...");
        thread2.start();
    }


}
