package concurrent.concurrency.safety;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author shaoxubao
 * @Date 2019/10/15 14:08
 */
public class CasDemo {
    private volatile int noCasCount = 0;
    private volatile int casCount = 0;
    private static Unsafe UNSAFE;

    /** casCount在CasDemo的内存偏移量*/
    private static final long casCountOffset;

    static {
        try {
            // Unsafe源码有一个属性是theUnsafe,通过反射获取
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null); // 在java的反射中,通过字段获取对象，不是静态字段不能传null
        } catch (Exception ignore) {
        }
        assert UNSAFE != null;
        try {
            casCountOffset = UNSAFE.objectFieldOffset(CasDemo.class.getDeclaredField("casCount"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    class NoCasThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; ++i) {
                ++noCasCount;
            }
        }
    }

    class CasThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000; ++i) {
                casIncrOne();
            }
        }

        private void casIncrOne() {
            int nowCount;
            do {
                nowCount = casCount; // 参数说明：casCountOffset:内存值(V), nowCount:旧的预期值(A), nowCount + 1:要修改的新值(B)
            } while (!UNSAFE         // 当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则返回V
                    .compareAndSwapInt(CasDemo.this, casCountOffset, nowCount, nowCount + 1));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CasDemo casDemo = new CasDemo();
        for (int i = 0; i < 10; ++i) {
            NoCasThread noCasThread = casDemo.new NoCasThread();
            noCasThread.start();
            CasThread casThread = casDemo.new CasThread();
            casThread.start();
        }

        Thread.sleep(1000);
        System.out.println("noCasCount = " + casDemo.noCasCount);
        System.out.println("casCount = " + casDemo.casCount);

    }
}
