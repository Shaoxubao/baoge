package concurrent.thread.jioujiaoti.type1;

/**
 * 定义要打印的资源
 */
public class Num {
    int i = 1;
    // 两个线程看， 交替执行的一个标志
    boolean flag = false;
}
