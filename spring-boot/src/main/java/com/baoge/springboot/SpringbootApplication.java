package com.baoge.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @SpringBootApplication标注一个主程序类，说明是一个Spring Boot应用
 */

//@ImportResource(locations = {"classpath:bean.xml"})
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
