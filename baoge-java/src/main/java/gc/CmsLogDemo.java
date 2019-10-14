package gc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * java版本jdk1.8.0_201
 *
 * 空间分配 eden=40m, s0=5m, s1=5m, old=50m
 *  * 启动参数
 *   -Xms100m -Xmx100m -Xmn50m -XX:+UseConcMarkSweepGC -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  -XX:+PrintHeapAtGC -Xloggc:./baoge-java/cms-gc.log
 *
 * @Author shaoxubao
 * @Date 2019/10/14 19:19
 */
public class CmsLogDemo {
    private static final int Mb = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        // eden
        System.out.println(getCurrentTime() + " cms before allocation1 ");
        byte[] allocation = new byte[32 * Mb];
        System.out.println(getCurrentTime() + " cms after allocation1 \n");
        Thread.sleep(2000);

        // 触发minor gc
        System.out.println(getCurrentTime() + " cms before allocation2 ");
        allocation = new byte[3 * Mb];
        System.out.println(getCurrentTime() + " cms after allocation2 \n");
        Thread.sleep(2000);

        byte[][] allByte = new byte[10][];

        // old gc
        for (int i = 0; i < 10; ++i) {
            System.out.println(getCurrentTime() + " cms before list allocation, index = " + i);
            allByte[i] = new byte[3 * Mb];

            System.out.println(getCurrentTime() + " cms after list allocation, index = " + i + "\n");
            Thread.sleep(2000);
        }
        Thread.sleep(3000);
    }

    private static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
