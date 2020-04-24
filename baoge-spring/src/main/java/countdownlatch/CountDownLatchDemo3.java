package countdownlatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import utils.ListUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author shaoxubao
 * @Date 2020/4/23 16:15
 */
public class CountDownLatchDemo3 {

    private static final int EXECUTOR_SIZE = 10;

    final static CountDownLatch countDownLatch = new CountDownLatch(EXECUTOR_SIZE); // 10

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean.xml");
        ThreadPoolTaskExecutor threadPoolTaskExecutor = context.getBean(ThreadPoolTaskExecutor.class);

        List<List<Integer>> batchItemIdList = ListUtils.averageAssign(Arrays.asList(1, 2, 3), EXECUTOR_SIZE);

        long startTime = System.currentTimeMillis();
        for (final List<Integer> itemIds : batchItemIdList) {
            threadPoolTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(itemIds);
                        Thread.sleep(200);
                    } catch (Exception e) {

                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        // 主线程等待
        boolean flag = true;
        try {
            flag = countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (Exception e) {

        }

        System.out.println("执行完毕flag:" + flag + ", 耗时：" + (System.currentTimeMillis() - startTime));

        ((ClassPathXmlApplicationContext) context).close();
    }

}
