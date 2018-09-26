package base;

/**
 * 位运算
 */
public class BitDemo {

    public static void main(String[] args) {

        int a = 2 << 3; //  2的二进制是：00010， 左移3位得到00010000，即2 X 3 = 16

        int b = 16 >> 2; // 16转化为二进制为：10000，然后右移两位得到00100，结果是4

        System.out.println("a = " + a + ", b = " + b);

    }

}
