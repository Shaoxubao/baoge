package com.baoge;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author shaoxubao
 * @Date 2019/5/18 11:30
 */

@ContextConfiguration({"classpath:META-INF/spring/*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {
}
