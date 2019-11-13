package pattern.singleton;

/**
 * @Author shaoxubao
 * @Date 2019/8/23 9:47
 *
 * 单例模式示例,双重锁定检查
 */
public class DoubleCheckedLockingSingleton {

    private volatile DoubleCheckedLockingSingleton INSTANCE;

    private DoubleCheckedLockingSingleton() {}

    public DoubleCheckedLockingSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized(DoubleCheckedLockingSingleton.class) {
                // double checking Singleton instance
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedLockingSingleton();
                }
            }
        }

        return INSTANCE;
    }
}
