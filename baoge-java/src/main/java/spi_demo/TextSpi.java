package spi_demo;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author shaoxubao
 * @Date 2019/5/20 15:34
 */
public class TextSpi {
    public static void main(String[] args) {

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

    }
}
