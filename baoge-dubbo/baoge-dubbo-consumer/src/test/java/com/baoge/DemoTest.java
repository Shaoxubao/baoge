package com.baoge;

import com.baoge.demo.DemoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author shaoxubao
 * @Date 2019/5/18 11:34
 */
public class DemoTest extends BaseTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void testDemo() {

        while (true) {
            String hello = demoService.sayHello("hello world");
            System.out.println(hello);
        }
    }
}
