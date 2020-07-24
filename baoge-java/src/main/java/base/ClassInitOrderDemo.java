package base;

/**
 * @Author shaoxubao
 * @Date 2020/7/24 10:13
 */
public class ClassInitOrderDemo {

    public static void main(String[] args) {
        Derived derived = new Derived();
    }

}

class Log {
    public static String initLog(String log) {
        System.out.println(log);
        return null;
    }
}

/**
 * 基类
 */
class Base {
    static {
        System.out.println("Base Static Block 1");
    }

    private static String staticValue = Log.initLog("Base Static Field");

    static {
        System.out.println("Base Static Block 2");
    }

    {
        System.out.println("Base Normal Block 1");
    }

    private String value = Log.initLog("Base Normal Field");

    {
        System.out.println("Base Normal Block 2");
    }

    Base() {
        System.out.println("Base Constructor");
    }

}

/**
 * 派生类
 */
class Derived extends Base {
    static {
        System.out.println("Derived Static Block 1");
    }

    static {
        System.out.println("Derived Static Block 2");
    }

    {
        System.out.println("Derived Normal Block 1");
    }

    private String value = Log.initLog("Derived Normal Field");

    {
        System.out.println("Derived Normal Block 2");
    }

    Derived() {
        System.out.println("Derived Constructor");
    }
}
