package spi_demo.dubbo.ioc_inject;

import com.alibaba.dubbo.common.URL;
import spi_demo.dubbo.Person;
import spi_demo.dubbo.World;

public class Earth implements World {

    // 扩展点自动注入
    // 并且注入的为扩展点适配类
    private Person person;

    @Override
    public void day(URL url) {
        System.out.println("地球美好的一天开始");
        person.day(url);
        System.out.println("地球美好的一天结束");
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
