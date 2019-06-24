package concurrent.thread.threadpool;

import java.util.concurrent.*;

/**
 * @Author shaoxubao
 * @Date 2019/6/24 10:44
 */
public class ThreadPoolService {
    private static final ThreadPoolExecutor threadPool;

    public ThreadPoolService() {
    }

    public static void execute(Runnable task) {
        threadPool.submit(task);
    }

    public static <T> Future<T> execute(Callable<T> callable) {
        return threadPool.submit(callable);
    }

    static {
        threadPool = new ThreadPoolExecutor(50, 500, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue(10000), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void shutDown() {
        threadPool.shutdown();
    }
}
