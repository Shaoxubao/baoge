package jdk8.my_concurrenthashmap;

/**
 * @Author shaoxubao
 * @Date 2020/7/22 11:13
 */
public class TestMyMyHashMap {

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap();

        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");
        map.put(6, "f");
        map.put(7, "g");
        map.put(8, "e");
        map.put(9, "f");
        map.put(10, "g");
        map.put(17, "gg");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));
        System.out.println(map.get(5));
        System.out.println(map.get(6));
        System.out.println(map.get(7));
        System.out.println(map.get(8));
        System.out.println(map.get(9));
        System.out.println(map.get(10));
        System.out.println(map.get(17));


    }

}
