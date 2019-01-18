package proxy;

import pattern.proxy.DynamicProxyHandler;
import pattern.proxy.HelloService;
import pattern.proxy.HelloServiceImpl;

public class SimpleProxyDemo {
    public static void main(String[] args) throws SecurityException, NoSuchMethodException {
        HelloServiceImpl c = new HelloServiceImpl();

        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(c);
        HelloService proxyClass = (HelloService)proxyHandler.newProxyInstance();

        System.out.println(proxyClass.getClass().getName());
        System.out.println(proxyClass.sayHello("hello world!"));
    }
}
