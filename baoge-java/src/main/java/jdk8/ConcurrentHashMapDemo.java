package jdk8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        Map<Integer, String> hashMap = new ConcurrentHashMap<>(3);

        hashMap.put(1, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        hashMap.put(4, "d");
        hashMap.put(5, "e");
        hashMap.put(6, "f");
        hashMap.put(7, "g");
        hashMap.put(8, "h");
        hashMap.put(9, "i");
        hashMap.size();

        // 位运算
        int n = 5;

        n |= n >>> 1;
        System.out.println(n + "——>" + Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(n + "——>" + Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(n + "——>" + Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(n + "——>" + Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(n + "——>" + Integer.toBinaryString(n));

    }

}
