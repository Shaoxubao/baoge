package jdk8.my_concurrenthashmap;

import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/7/1 16:02
 */
public class Test {

    public static void main(String[] args) {
        Map<Integer, String> hashMap = new MyConcurrentHashMap<>(3);

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
    }
}
