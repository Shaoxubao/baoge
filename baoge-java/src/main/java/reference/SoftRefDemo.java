package reference;

import java.lang.ref.SoftReference;

/**
 * 功能：软引用demo
 * 详情：
 * @Author shaoxubao
 * @Date 2019/10/15 14:08
 */
public class SoftRefDemo {
    public static void main(String[] args) {
        SoftReference<String> sr = new SoftReference<>( new String("hello world "));
        System.out.println(sr.get());
    }
}
