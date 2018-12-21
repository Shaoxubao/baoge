package concurrent.thread.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedThreadPoolHandler implements RejectedExecutionHandler {

    public RejectedThreadPoolHandler() {
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("WARNING 自定义拒绝策略: Task " + r.toString() + " rejected from " + executor.toString());
    }

}
