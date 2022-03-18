package jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest4 {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "baoge");
        String[] array = stream.toArray(length -> new String[length]); // 方法引用方式：String[]::new
        Arrays.asList(array).forEach(System.out::println);

        System.out.println("--------");

        Stream<String> stream2 = Stream.of("hello", "world", "baoge");
        List<String> list = stream2.collect(Collectors.toList()); // 流转换为list

        System.out.println("--------");

        Stream<String> stream3 = Stream.of("hello", "world", "baoge");
//        List<String> list1 = stream3.collect(() -> new ArrayList<>(), (theList, item) -> theList.add(item),
//                (theList1, theList2) ->theList1.addAll(theList2));
        List<String> list2 = stream3.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
//        List<String> list3 = stream3.collect(Collectors.toCollection(ArrayList::new));
        list2.forEach(System.out::println);
        System.out.println("--------");

        Stream<String> stream4 = Stream.of("hello", "world", "baoge");
        Set<String> set = stream4.collect(Collectors.toCollection(TreeSet::new));
        set.forEach(System.out::println);
        System.out.println("--------");

        Stream<String> stream5 = Stream.of("hello", "world", "baoge");
        String str = stream5.collect(Collectors.joining());
        System.out.println(str);
        System.out.println("--------");
    }
}
