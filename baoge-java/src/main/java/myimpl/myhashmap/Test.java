package myimpl.myhashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/11
 */
public class Test {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");

        System.out.println(map);


        Map<Integer, String> concMap = new ConcurrentHashMap<>();
        concMap.put(1, "a");
        concMap.put(2, "b");
        concMap.put(3, "c");
        concMap.put(4, "d");
        System.out.println(concMap);
    }



}
