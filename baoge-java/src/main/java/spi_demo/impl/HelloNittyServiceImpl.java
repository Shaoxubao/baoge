package spi_demo.impl;

import spi_demo.IHelloService;

/**
 * @Author shaoxubao
 * @Date 2019/5/20 15:15
 */
public class HelloNittyServiceImpl implements IHelloService {
    @Override
    public void sayHello() {
        System.out.println("hello nitty.");
    }
}
