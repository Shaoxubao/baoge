package interview.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/4
 */
public class CGLibExample {

    static class Car {
        public void isRunning() {
            System.out.println("the car is running...");
        }
    }

    /**
     * CGLib 代理类
     */
    static class CGLibProxy implements MethodInterceptor {

        private Object target;

        public Object getInstance(Object target) {
            this.target = target;
            Enhancer enhancer = new Enhancer();
            // 设置父类为实例类
            enhancer.setSuperclass(this.target.getClass());
            // 回调方法
            enhancer.setCallback(this);
            // 创建代理对象
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("方法调用前业务处理......");
            Object result = methodProxy.invokeSuper(o, objects); // 执行方法调用
            System.out.println("方法调用后业务处理......");
            return result;
        }
    }

    public static void main(String[] args) {
        // 将动态生成的每个class都输出到文件中
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\data");

        CGLibProxy cgLibProxy = new CGLibProxy();
        Car car = (Car) cgLibProxy.getInstance(new Car());
        car.isRunning();
    }

}
