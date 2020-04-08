package interview.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

/**
 * @Author shaoxubao
 * @Date 2020/4/8 17:51
 */
public class FastClassTest {

    public static void main(String args[]) throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\data");

        FastClass clazz = FastClass.create(FastSource.class);
        // fast class反射调用
        FastSource obj = (FastSource) clazz.newInstance();
        clazz.invoke("setValue", new Class[] { int.class }, obj, new Object[] { 1 });
        clazz.invoke("setOther", new Class[] { int.class }, obj, new Object[] { 2 });

        int value = (Integer) clazz.invoke("getValue", new Class[] {}, obj, new Object[] {});
        int other = (Integer) clazz.invoke("getOther", new Class[] {}, obj, new Object[] {});
        System.out.println(value + " " + other);
        // fastMethod使用
        FastMethod setValue = clazz.getMethod("setValue", new Class[] { int.class });
        System.out.println("setValue index is : " + setValue.getIndex());

        FastMethod getValue = clazz.getMethod("getValue", new Class[] {});
        System.out.println("getValue index is : " + getValue.getIndex());

        FastMethod setOther = clazz.getMethod("setOther", new Class[] { int.class });
        System.out.println("setOther index is : " + setOther.getIndex());

        FastMethod getOther = clazz.getMethod("getOther", new Class[] {});
        System.out.println("getOther index is : " + getOther.getIndex());
        // 其他
        System.out.println("getDeclaredMethods : " + clazz.getJavaClass().getDeclaredMethods().length);
        System.out.println("getConstructors : " + clazz.getJavaClass().getConstructors().length);
        System.out.println("getFields : " + clazz.getJavaClass().getFields().length);
        System.out.println("getMaxIndex : " + clazz.getMaxIndex());
    }

}
class FastSource {
    private int value;
    private int other;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}