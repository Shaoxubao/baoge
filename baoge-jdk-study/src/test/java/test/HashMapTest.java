package test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shaoxubao
 * @Date 2020/7/3 17:31
 */
public class HashMapTest {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        System.out.println(map);
    }

}
