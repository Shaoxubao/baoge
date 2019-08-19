package guava;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.*;
import guaua.Person;
import org.junit.Test;

import java.util.*;

/**
 * @Author shaoxubao
 * @Date 2019/6/11 17:21
 */
public class TestGuava {

    /**
     * 用'_'拼接
     */
    @Test
    public void testSkipNulls() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        String result = Joiner.on("_").skipNulls().join(list);

        System.out.println(result);
    }

    /**
     * 分组
     */
    @Test
    public void testGroup() {
        Person p1 = new Person("a", 10, "男", "china");
        Person p2 = new Person("b", 11, "女", "japan");
        Person p3 = new Person("c", 12, "男", "japan");
        Person p4 = new Person("d", 14, "男", "USA");
        Person p5 = new Person("e", 14, "女", "china");
        List<Person> persons = Lists.newArrayList(p1, p2, p3, p4, p5);
        Multimap<String,Person> multimap1 = ArrayListMultimap.create();
        Multimap<Integer,Person> multimap2 = ArrayListMultimap.create();
        Multimap<String,Person> multimap3 = ArrayListMultimap.create();
        for (Person p : persons) {
            multimap1.put(p.getSex(), p);
            multimap2.put(p.getAge(), p);
            multimap3.put(p.getCountry(), p);
        }

        Map<String, Collection<Person>> sex = multimap1.asMap();
        for (Map.Entry<String, Collection<Person>> p : sex.entrySet()) {
            System.out.println("按性别分组 = " +p.getKey()+"--"+ p.getValue());
        }
        Map<Integer, Collection<Person>> age = multimap2.asMap();
        for (Map.Entry<Integer, Collection<Person>> p : age.entrySet())
            System.out.println("按年龄分组 = " + p.getKey() + "岁" + "--" + p.getValue());
        Map<String, Collection<Person>> country = multimap3.asMap();
        for (Map.Entry<String, Collection<Person>> p : country.entrySet()) {
            System.out.println("按国家分组 = " +p.getKey() +"--" + p.getValue());
        }
    }

    /**
     * 过滤
     */
    @Test
    public void testFilter() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<String> result = Collections2.filter(names, Predicates.containsPattern("a"));
        // [Jane, Adam]
        System.out.println(result);


        List<Integer> collections = Lists.newArrayList(1, 2, 3, 4);
        Collection<Integer> filter = Collections2.filter(collections, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input >= 3;
            }
        });
        // [3, 4]
        System.out.println(filter);

        // 将多个prdicate进行组合
        List<String> names2 = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<String> result2 = Collections2.filter(names2,
                        Predicates.or(Predicates.containsPattern("J"), Predicates.not(Predicates.containsPattern("a"))));
        // [John, Jane, Tom]
        System.out.println(result2);


        // 检查一个collection中的所有元素是否符合某个条件:
        List<String> names1 = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        boolean result1 = Iterables.all(names1, Predicates.containsPattern("n|m"));
        // true
        System.out.println(result1);
        result1 = Iterables.all(names1, Predicates.containsPattern("a"));
        // false
        System.out.println(result1);

        // 过滤
        List<Integer> numList = Arrays.asList(1, 3, 5, 6, 54, 9, 8, 9, 90);
        Integer r = 9;
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return r.equals(input);
            }
        };

        Collection<Integer> collectionsResult = Collections2.filter(numList, predicate);
        System.out.println(collectionsResult);

    }

}
