package pattern.singleton;

/**
 * @Author shaoxubao
 * @Date 2019/8/23 9:48
 *
 *  单例模式示例与静态工厂方法
 */
public class Singleton {

    // initailzed during class loading
    private static final Singleton INSTANCE = new Singleton();

    // to prevent creating another instance of Singleton
    private Singleton() {}

    public static Singleton getSingleton() {
        return INSTANCE;
    }
}
