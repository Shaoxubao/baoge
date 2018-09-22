package ioc_simple_demo;

import ioc_simple_demo.util.ApplicationContext;

public class IocSimpleDemo {

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ApplicationContext("applicationContext.xml");

        Person person = (Person) applicationContext.getBean("p");

        System.out.println(person);

    }

}
