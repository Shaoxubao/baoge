package interview.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/4
 */
public class JDKProxyExample {

    static interface Car {
        void isRunning();
    }

    static class Bus implements Car {
        @Override
        public void isRunning() {
            System.out.println("the bus is running.......");
        }
    }

    static class Taxi implements Car {
        @Override
        public void isRunning() {
            System.out.println("the taxi is running......");
        }
    }


    static class JDKProxy implements InvocationHandler {

        private Object target; // 代理对象

        /**
         * 获取代理对象
         */
        public <T> T getProxy(Object target) {
            this.target = target;
            return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            System.out.println("动态代理之前业务处理......");
            Object result = method.invoke(target, args); // 执行调用方法（此方法执行前后，可以进行相关业务处理）
            System.out.println("方法调用后业务处理......");
            return result;
        }
    }

    public static void main(String[] args) {
        JDKProxy jdkProxy = new JDKProxy();
        Car carInstance = jdkProxy.getProxy(new Taxi());
        carInstance.isRunning();
    }

}
