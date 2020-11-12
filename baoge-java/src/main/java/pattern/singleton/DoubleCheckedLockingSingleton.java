package pattern.singleton;

/**
 * @Author shaoxubao
 * @Date 2019/8/23 9:47
 *
 * 单例模式示例,双重锁定检查
 */
public class DoubleCheckedLockingSingleton {

    private volatile static DoubleCheckedLockingSingleton INSTANCE;

    // 构造器私有化
    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
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
