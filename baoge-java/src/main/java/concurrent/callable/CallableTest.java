package concurrent.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author shaoxubao
 * @Date 2019/8/19 17:24
 */
public class CallableTest {

    public static void main(String[] args) {

        List<CallablePrint> taskList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            taskList.add(new CallablePrint(i));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);  // 初始化定长线程池
        try {
            List<Future<Integer>> futureList = executorService.invokeAll(taskList);
            for (Future<Integer> future : futureList) { // 取出结果
                System.out.println("==============num:" + " = " + future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();                // 关闭线程池
        }
    }

    static class CallablePrint implements Callable<Integer> {
        private Integer num;

        public CallablePrint(Integer num) {
            this.num = num;
        }

        @Override
        public Integer call() {
            return num;
        }
    }

}
