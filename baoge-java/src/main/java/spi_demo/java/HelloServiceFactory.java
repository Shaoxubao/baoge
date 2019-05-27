package spi_demo.java;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author shaoxubao
 * @Date 2019/5/27 14:14
 */
public class HelloServiceFactory {

    public HelloServiceFactory() {}

    public static IHelloService newIHelloService(String name) {
        IHelloService helloService = null;

        ServiceLoader<IHelloService> loaders = ServiceLoader.load(IHelloService.class);

        Iterator<IHelloService> serviceIterator = loaders.iterator();
        while (serviceIterator.hasNext()) {
            IHelloService iHelloServiceTmp = serviceIterator.next();
            if (iHelloServiceTmp.getClass().toString().contains(name)) {
                helloService = iHelloServiceTmp;
                break;
            }
        }

        return helloService;
    }

}
