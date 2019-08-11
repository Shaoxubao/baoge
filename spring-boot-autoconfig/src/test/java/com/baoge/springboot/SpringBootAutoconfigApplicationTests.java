package com.baoge.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAutoconfigApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {

        // 日志级别，由低到高
        // 可以调整输出的日志级别，只会在这个级别后的高级别生效
        logger.trace("这是trace");
        logger.debug("这是debug");
        // Spring Boot默认设置的级别是info
        logger.info("这是info");
        logger.warn("这是warn");
        logger.error("这是error");


    }

}
