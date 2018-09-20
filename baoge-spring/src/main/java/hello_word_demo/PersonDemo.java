package hello_word_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reflect.Person;

public class PersonDemo {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean.xml");

        Person person = context.getBean(Person.class);

        System.out.println(person.work());
    }
}
