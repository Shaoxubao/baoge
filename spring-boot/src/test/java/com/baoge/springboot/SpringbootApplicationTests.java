package com.baoge.springboot;

import com.baoge.springboot.yamlconfig.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Spring Boot单元测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	Person person;

	@Test
	public void contextLoads() {

		System.out.println(person);

	}

}
