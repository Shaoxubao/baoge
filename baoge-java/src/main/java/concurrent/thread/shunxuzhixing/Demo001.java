package concurrent.thread.shunxuzhixing;

/**
 * @Author shaoxubao
 * @Date 2020/2/19 14:28
 *
 *
 * join(): 是Thread的方法，作用是调用线程需等待该join()线程执行完成后，才能继续用下运行。
 *
 * 具体原理是在A线程中调用B线程的Join()方法，相当于A线程调用了B线程的wait()方法，那么只有当B线程执行完毕，A才会执行，这个期间A线程进入阻塞状态，
 * 这种方式是在启动线程的时候去顺序的控制。使得并行执行的任务串行化的执行。
 *
 * 应用场景: 当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
 *
 */
public class Demo001 {

    public static void main(String[] args) {

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理规划新需求");
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                    System.out.println("开发人员开发新需求功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
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
