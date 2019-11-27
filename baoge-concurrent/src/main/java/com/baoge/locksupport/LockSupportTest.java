package com.baoge.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author shaoxubao
 * @Date 2019/11/27 15:02
 *
 * 打jar包，在服务器上运行（java -cp baoge-concurrent-1.0-SNAPSHOT.jar com.baoge.locksupport.LockSupportTest）
 * jstack pid 查看线程堆栈信息：
 * LockSupport.park() 与 LockSupport.park(this) 查看结果是不同的，后者(设置blocker参数)会打印是哪个类调用park
 */
public class LockSupportTest {

    public void testPark() {
        LockSupport.park(this);
    }

    public static void main(String[] args) {
        LockSupportTest test = new LockSupportTest();
        test.testPark();
    }
}
