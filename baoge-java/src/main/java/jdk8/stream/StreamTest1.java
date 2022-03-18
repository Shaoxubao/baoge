package jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest1 {
    public static void main(String[] args) {
        // 构造流的方式
        Stream stream1 = Stream.of("hello", "world");
        String[] array = new String[]{"hello", "world"};
        Stream stream2 = Stream.of(array);
        Stream stream3 = Arrays.stream(array);

        List<String> list = Arrays.asList(array);
        Stream stream4 = list.stream();
    }
}
