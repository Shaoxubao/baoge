package com.baoge.springboot;

import com.baoge.springboot.yamlconfig.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Spring Boot单元测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	Person person;

	@Autowired
    ApplicationContext ioc;

	@Test
    public void testHelloService() {

	    boolean r = ioc.containsBean("helloService");
	    System.out.println(r);
    }

	@Test
	public void contextLoads() {

		System.out.println(person);

	}

}
