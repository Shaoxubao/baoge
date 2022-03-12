package jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Test1 {
    public static void main(String[] args) {
        TheInterface i1 = () -> {};
        System.out.println(i1.getClass().getInterfaces()[0]);

        TheInterface2 i2 = () -> {};
        System.out.println(i2.getClass().getInterfaces()[0]);

        new Thread(() -> System.out.println("hello")).start();

        List<String> list = Arrays.asList("hello", "world", "baoge");
        list.forEach(item -> System.out.println(item.toUpperCase()));

        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        list.stream().map(String::toUpperCase).forEach(item -> System.out.println(item));
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);

        List<String> list2 = Arrays.asList("hello", "world", "baoge");
        // expression o1.compareTo(o2)     表达式
        // statement {return o1.compareTo(o2);}  语句
        Collections.sort(list2, ((o1, o2) -> o1.compareTo(o2)));
        Collections.sort(list2, ((o1, o2) -> {return o1.compareTo(o2);}));
        System.out.println(list2);

    }
}

@FunctionalInterface
interface TheInterface {
    void method1();
}

@FunctionalInterface
interface TheInterface2 {
    void method2();
}
