package jdk8;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    static final int HASH_BITS = 0x7fffffff;

    public static void main(String[] args) {

        Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>(3);

        concurrentHashMap.put(1, "a");
        concurrentHashMap.put(2, "b");
        concurrentHashMap.put(3, "c");
        concurrentHashMap.put(4, "d");
        concurrentHashMap.put(5, "e");
        concurrentHashMap.put(6, "f");
        concurrentHashMap.put(7, "g");
        concurrentHashMap.put(8, "h");
        concurrentHashMap.put(9, "i");
        System.out.println(concurrentHashMap.size());

        for (Map.Entry<Integer, String> entry : concurrentHashMap.entrySet()) {
            String value = entry.getValue();
            System.out.println(value);
        }

        Map<Integer, String> hashMap = new HashMap<>(3);

        hashMap.put(1, "a");
        hashMap.put(2, "b");
        hashMap.put(3, "c");
        hashMap.put(4, "d");
        hashMap.put(5, "e");
        hashMap.put(6, "f");
        hashMap.put(7, "g");
        hashMap.put(8, "h");
        hashMap.put(9, "i");
        System.out.println(hashMap.size());

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

        // ===========
        Integer a = 1, b = 2, c = 11, d = 9, e = 13;
        System.out.println(15 & spread(a.hashCode())); // 1111
        System.out.println(15 & spread(b.hashCode()));
        System.out.println(15 & spread(c.hashCode()));
        System.out.println(15 & spread(d.hashCode()));
        System.out.println(15 & spread(e.hashCode()));

        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i = 0; i < 20000; i++) {
            int hash = spread(i.hashCode());
            map.put(hash, i);
        }

        System.out.println(map);
    }

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }

}
