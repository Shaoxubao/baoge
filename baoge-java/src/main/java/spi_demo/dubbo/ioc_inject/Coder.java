package spi_demo.dubbo.ioc_inject;

import com.alibaba.dubbo.common.URL;
import spi_demo.dubbo.Person;

public class Coder implements Person {

    @Override
    public void day(URL url) {
        System.out.println("写代码");
    }
}
