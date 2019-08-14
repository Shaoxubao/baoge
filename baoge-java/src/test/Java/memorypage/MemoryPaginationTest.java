package memorypage;

import guaua.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/8/14 17:13
 */
public class MemoryPaginationTest {

    @Test
    public void testMemoryPagination() {

        List<Person> personList = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            Person person = new Person("name" + i, i, "", "");
            personList.add(person);
        }

        MemoryPagination pagination1 = new MemoryPagination(1, 2, personList);
        System.out.println(pagination1.getData());
        System.out.println("==================");

        MemoryPagination pagination2 = new MemoryPagination(2, 2, personList);
        System.out.println(pagination2.getData());
        System.out.println("==================");

        MemoryPagination pagination3 = new MemoryPagination(3, 2, personList);
        System.out.println(pagination3.getData());
        System.out.println("==================");

    }

}
