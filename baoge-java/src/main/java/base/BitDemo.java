package base;

/**
 * 位运算
 */
public class BitDemo {

    public static void main(String[] args) {

        int a = 2 << 3; //  2的二进制是：00010， 左移3位得到00010000，即2 X 3 = 16

        int b = 16 >> 2; // 16转化为二进制为：10000，然后右移两位得到00100，结果是4

        System.out.println("a = " + a + ", b = " + b);



        int result = hash(2);

        System.out.println("result=" + result);

    }

    /**
     * 异或
     */
    public static int hash(Object key) {
        int h; // key.hashCode()：返回散列值也就是hashcode
        // ^ ：按位异或
        // >>>:无符号右移，忽略符号位，空位都以0补齐
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
