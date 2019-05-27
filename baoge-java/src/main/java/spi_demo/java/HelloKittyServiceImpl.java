package spi_demo.java;

/**
 * @Author shaoxubao
 * @Date 2019/5/20 15:15
 */
public class HelloKittyServiceImpl implements IHelloService {
    @Override
    public void sayHello() {
        System.out.println("hello kitty.");
    }
}
