package transaction.demo6_annotation;

import transaction.demo3_transaction_holder.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 * Describe: 使用注解标记事务的基本原理为：依然使用demo5中讲到的动态代理的方式，只是在InvocationHandler的invoke方法中，
 *           首先判断被代理的方法是否标记有Transactional注解，如果没有则直接调用method.invoke(proxied, objects)，
 *           否则，先准备事务，在调用method.invoke(proxied, objects)，然后根据该方法是否执行成功调用commit或rollback。
 *           定义TransactionEnabledAnnotationProxyManager如下：
 */
public class TransactionEnabledAnnotationProxyManager {

    private TransactionManager transactionManager;

    public TransactionEnabledAnnotationProxyManager(TransactionManager transactionManager) {

        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new AnnotationTransactionInvocationHandler(object, transactionManager));
    }

}

class AnnotationTransactionInvocationHandler implements InvocationHandler {
    private Object proxied;
    private TransactionManager transactionManager;

    AnnotationTransactionInvocationHandler(Object object, TransactionManager transactionManager) {
        this.proxied = object;
        this.transactionManager = transactionManager;
    }

    /**
     * 可以看到，在AnnotationTransactionInvocationHandler的invoke方法中，我们首先获得原service的transfer方法，
     * 然后根据originalMethod.isAnnotationPresent(Transactional.class)判断该方法是否标记有Transactional注解，
     * 如果没有，则任何额外功能都不加，直接调用原来service的transfer方法；否则，将其加入到事务处理中。
     */
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        Method originalMethod = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (!originalMethod.isAnnotationPresent(Transactional.class)) {
            return method.invoke(proxied, objects);
        }
        // 有事务注解将其加入事务中
        transactionManager.start();
        Object result = null;
        try {
            result = method.invoke(proxied, objects);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }
        return result;
    }
}
