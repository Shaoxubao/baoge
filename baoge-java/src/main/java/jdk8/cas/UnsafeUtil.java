package jdk8.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe工具类
 */
public class UnsafeUtil {
    /**
     * 通过反射获得sun.misc.Unsafe实例<br/>
     * see https://stackoverflow.com/questions/10829281/is-it-possible-to-use-java-unsafe-in-user-code
     */
    public static Unsafe getUnsafe() {
        try {
            // 通过反射得到theUnsafe对应的Field对象
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            // theUnsafe是static，因此传入null即可
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new SecurityException("Unsafe");
        }
    }

    public static void main(String[] args) {
        System.out.println(getUnsafe()); // sun.misc.Unsafe@2626b418
    }
}
