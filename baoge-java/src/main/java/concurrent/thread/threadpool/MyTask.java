package concurrent.thread.threadpool;

public class MyTask implements Runnable {

    private int taskId;          // 任务 id
    private String taskName;     // 任务名字

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public MyTask(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("当前正在执行 ******   线程Id-->" + taskId + ",任务名称-->" + taskName);
        try {
            Thread.currentThread().sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程Id-->" + taskId + ",任务名称-->" + taskName + "   -----------   执行完毕！");
    }

}
