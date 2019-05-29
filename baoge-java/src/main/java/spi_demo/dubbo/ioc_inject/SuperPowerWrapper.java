package spi_demo.dubbo.ioc_inject;

import com.alibaba.dubbo.common.URL;
import spi_demo.dubbo.Person;

/**
 * @Author shaoxubao
 * @Date 2019/5/29 15:11
 */

/**
 * 扩展点自动包装
 * Dubbo 认为的包装类需要满足的两个条件：
 * 1.持有扩展点接口对象属性，并通过构造器方式初始化该属性
 * 2.这个类也要实现扩展点接口类，并在实现方法中进行增强操作
 */
public class SuperPowerWrapper implements Person {

    private Person person;

    public SuperPowerWrapper(Person person) {
        this.person =person;
    }

    @Override
    public void day(URL url) {
        System.out.println("好了，你现在牛了，开始用以下车：");
        long begin = System.currentTimeMillis();

        person.day(url);

        long end = System.currentTimeMillis();
        System.out.println("用车耗时 cost:" + (end - begin) + " ms");
    }
}
