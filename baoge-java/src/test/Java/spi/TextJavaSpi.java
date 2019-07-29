package spi;

import org.junit.Test;
import spi_demo.java.HelloServiceFactory;
import spi_demo.java.IHelloService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author shaoxubao
 * @Date 2019/5/20 15:34
 */
public class TextJavaSpi {

    private static String name = "HelloNittyServiceImpl";

    @Test
    public void textJavaSpi() {

        ServiceLoader<IHelloService> loaders = ServiceLoader.load(IHelloService.class);

        // 遍历方式一
        for (IHelloService service : loaders) {
            service.sayHello();
        }

        // 遍历方式二
        Iterator<IHelloService> operationIterator = loaders.iterator();
        while (operationIterator.hasNext()) {
            IHelloService service = operationIterator.next();
            service.sayHello();
        }

        // 遍历方式三
        IHelloService helloService = HelloServiceFactory.newIHelloService(name);
        helloService.sayHello();

    }
}
