package interview.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/4
 */
public class JDKProxyExampleV2 {

    static interface Calculator {
        int add (int a, int b);
        int subtract(int a, int b);
    }

    static class CalculatorImpl implements Calculator {

        @Override
        public int add (int a, int b) {
            return a + b;
        }

        @Override
        public int subtract(int a, int b) {
            return a - b;
        }
    }

    public static void main(String[] args) throws Exception {

        Class clazz = Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class);

        // 得到有参构造器
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);

        // 反射创建代理实例
        Calculator calculatorProxy = (Calculator) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 手动new一个目标对象
                CalculatorImpl calculatorImpl = new CalculatorImpl();
                // 反射执行目标对象的方法
                Object result = method.invoke(calculatorImpl, args);
                return result;
            }
        });

        System.out.println(calculatorProxy.add(1, 2));
        System.out.println(calculatorProxy.subtract(1, 2));
    }

}
