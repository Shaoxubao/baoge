package com.baoge.java_jihe;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/10/20
 */
public class IntersectionUnionSet {

    public static void main(String[] args) {
        testUnion();
        testIntersection();
        testDifference();
    }

    // 并集
    public static void testUnion() {
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();

        s1.add("a");
        s1.add("b");
        s1.add("c");

        s2.add("c");
        s2.add("e");
        s2.add("f");

        s1.addAll(s2);
        System.out.println("并集结果：" + s1);
    }

    // 交集
    public static void testIntersection() {
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();

        s1.add("a");
        s1.add("b");
        s1.add("c");

        s2.add("c");
        s2.add("e");
        s2.add("f");

        s1.retainAll(s2);
        System.out.println("交集结果：" + s1);
    }

    // 差集
    public static void testDifference() {
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();

        s1.add("a");
        s1.add("b");
        s1.add("c");

        s2.add("c");
        s2.add("e");
        s2.add("f");

        s1.removeAll(s2);
        System.out.println("差集结果：" + s1);
    }

}
