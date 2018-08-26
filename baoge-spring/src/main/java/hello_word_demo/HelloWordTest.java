package hello_word_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWordTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("");
        HelloWordService helloWordService = applicationContext.getBean(HelloWordService.class);
        helloWordService.sayHello();
    }

}
