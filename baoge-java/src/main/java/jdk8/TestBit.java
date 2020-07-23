package jdk8;

/**
 * @Author shaoxubao
 * @Date 2020/7/23 9:56
 */
public class TestBit {

    public static void main(String[] args) {
        int n = 8; // 1000

        System.out.println("================ n = 8 ===============");

        // tabAt(tab, i = (n - 1) & hash)
        System.out.println("(n - 1) & 1 = " +  Integer.toBinaryString((n - 1) & 1));
        System.out.println("(n - 1) & 2 = " +  Integer.toBinaryString((n - 1) & 2));
        System.out.println("(n - 1) & 3 = " +  Integer.toBinaryString((n - 1) & 3));
        System.out.println("(n - 1) & 4 = " +  Integer.toBinaryString((n - 1) & 4));
        System.out.println("(n - 1) & 5 = " +  Integer.toBinaryString((n - 1) & 5));
        System.out.println("(n - 1) & 6 = " +  Integer.toBinaryString((n - 1) & 6));
        System.out.println("(n - 1) & 7 = " +  Integer.toBinaryString((n - 1) & 7));
        System.out.println("(n - 1) & 8 = " +  Integer.toBinaryString((n - 1) & 8));
        System.out.println("(n - 1) & 9 = " +  Integer.toBinaryString((n - 1) & 9));
        System.out.println("(n - 1) & 10 = " +  Integer.toBinaryString((n - 1) & 10));
        System.out.println("(n - 1) & 11 = " +  Integer.toBinaryString((n - 1) & 11));
        System.out.println("(n - 1) & 12 = " +  Integer.toBinaryString((n - 1) & 12));
        System.out.println("(n - 1) & 13 = " +  Integer.toBinaryString((n - 1) & 13));
        System.out.println("(n - 1) & 14 = " +  Integer.toBinaryString((n - 1) & 14));
        System.out.println("(n - 1) & 15 = " +  Integer.toBinaryString((n - 1) & 15));
        System.out.println("(n - 1) & 16 = " +  Integer.toBinaryString((n - 1) & 16));
        System.out.println("(n - 1) & 17 = " +  Integer.toBinaryString((n - 1) & 17));

        System.out.println("-------------------------------");

        // int runBit = fh & n;
        System.out.println("1 & n = " +  Integer.toBinaryString(1 & n));
        System.out.println("2 & n = " +  Integer.toBinaryString(2 & n));
        System.out.println("3 & n = " +  Integer.toBinaryString(3 & n));
        System.out.println("4 & n = " +  Integer.toBinaryString(4 & n));
        System.out.println("5 & n = " +  Integer.toBinaryString(5 & n));
        System.out.println("6 & n = " +  Integer.toBinaryString(6 & n));
        System.out.println("7 & n = " +  Integer.toBinaryString(7 & n));
        System.out.println("8 & n = " +  Integer.toBinaryString(8 & n));
        System.out.println("9 & n = " +  Integer.toBinaryString(9 & n));
        System.out.println("10 & n = " +  Integer.toBinaryString(10 & n));
        System.out.println("11 & n = " +  Integer.toBinaryString(11 & n));
        System.out.println("12 & n = " +  Integer.toBinaryString(12 & n));
        System.out.println("13 & n = " +  Integer.toBinaryString(13 & n));
        System.out.println("14 & n = " +  Integer.toBinaryString(14 & n));
        System.out.println("15 & n = " +  Integer.toBinaryString(15 & n));
        System.out.println("16 & n = " +  Integer.toBinaryString(16 & n));
        System.out.println("17 & n = " +  Integer.toBinaryString(17 & n));

        System.out.println("================ n = 16 ===============");

        n = 16; // 10000
        // tabAt(tab, i = (n - 1) & hash)
        System.out.println("(16 - 1) & 1 = " +  Integer.toBinaryString((n - 1) & 1));
        System.out.println("(16 - 1) & 2 = " +  Integer.toBinaryString((n - 1) & 2));
        System.out.println("(16 - 1) & 3 = " +  Integer.toBinaryString((n - 1) & 3));
        System.out.println("(16 - 1) & 4 = " +  Integer.toBinaryString((n - 1) & 4));
        System.out.println("(16 - 1) & 5 = " +  Integer.toBinaryString((n - 1) & 5));
        System.out.println("(16 - 1) & 6 = " +  Integer.toBinaryString((n - 1) & 6));
        System.out.println("(16 - 1) & 7 = " +  Integer.toBinaryString((n - 1) & 7));
        System.out.println("(16 - 1) & 8 = " +  Integer.toBinaryString((n - 1) & 8));
        System.out.println("(16 - 1) & 9 = " +  Integer.toBinaryString((n - 1) & 9));
        System.out.println("(16 - 1) & 10 = " +  Integer.toBinaryString((n - 1) & 10));
        System.out.println("(16 - 1) & 11 = " +  Integer.toBinaryString((n - 1) & 11));
        System.out.println("(16 - 1) & 12 = " +  Integer.toBinaryString((n - 1) & 12));
        System.out.println("(16 - 1) & 13 = " +  Integer.toBinaryString((n - 1) & 13));
        System.out.println("(16 - 1) & 14 = " +  Integer.toBinaryString((n - 1) & 14));
        System.out.println("(16 - 1) & 15 = " +  Integer.toBinaryString((n - 1) & 15));
        System.out.println("(16 - 1) & 16 = " +  Integer.toBinaryString((n - 1) & 16));
        System.out.println("(16 - 1) & 17 = " +  Integer.toBinaryString((n - 1) & 17));

        System.out.println("-------------------------------");

        // int runBit = fh & n;
        System.out.println("1 & 16 = " +  Integer.toBinaryString(1 & n));
        System.out.println("2 & 16 = " +  Integer.toBinaryString(2 & n));
        System.out.println("3 & 16 = " +  Integer.toBinaryString(3 & n));
        System.out.println("4 & 16 = " +  Integer.toBinaryString(4 & n));
        System.out.println("5 & 16 = " +  Integer.toBinaryString(5 & n));
        System.out.println("6 & 16 = " +  Integer.toBinaryString(6 & n));
        System.out.println("7 & 16 = " +  Integer.toBinaryString(7 & n));
        System.out.println("8 & 16 = " +  Integer.toBinaryString(8 & n));
        System.out.println("9 & 16 = " +  Integer.toBinaryString(9 & n));
        System.out.println("10 & 16 = " +  Integer.toBinaryString(10 & n));
        System.out.println("11 & 16 = " +  Integer.toBinaryString(11 & n));
        System.out.println("12 & 16 = " +  Integer.toBinaryString(12 & n));
        System.out.println("13 & 16 = " +  Integer.toBinaryString(13 & n));
        System.out.println("14 & 16 = " +  Integer.toBinaryString(14 & n));
        System.out.println("15 & 16 = " +  Integer.toBinaryString(15 & n));
        System.out.println("16 & 16 = " +  Integer.toBinaryString(16 & n));
        System.out.println("17 & 16 = " +  Integer.toBinaryString(17 & n));
    }

}
