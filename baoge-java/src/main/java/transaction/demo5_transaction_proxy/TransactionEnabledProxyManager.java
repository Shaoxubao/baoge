package transaction.demo5_transaction_proxy;

import transaction.demo3_transaction_holder.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/4
 * Describe: Java动态代理的基本原理为：被代理对象需要实现某个接口（这是前提），代理对象会拦截对被代理对象的方法调用，
 *           在其中可以全然抛弃被代理对象的方法实现而完成另外的功能，也可以在被代理对象方法调用的前后增加一些额外的功能。
 *           在本篇文章中，我们将拦截service层的transfer方法，在其调用之前加入事务准备工作，然后调用原来的transfer方法，
 *           之后根据transfer方法是否执行成功决定commit还是rollback。
 */
public class TransactionEnabledProxyManager {

    private TransactionManager transactionManager;

    public TransactionEnabledProxyManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),
                new TransactionInvocationHandler(object, transactionManager));
    }

}

class TransactionInvocationHandler implements InvocationHandler {
    private Object proxy;
    private TransactionManager transactionManager;

    TransactionInvocationHandler(Object object, TransactionManager transactionManager)
    {
        this.proxy = object;
        this.transactionManager = transactionManager;
    }

    /**
     * 通过调用该类的proxyFor方法，传入需要被代理的对象（本例中为service对象），返回一个代理对象。
     * 此后，在调用代理对象的transfer方法时，会自动调用TransactionIvocationHandler的invoke方法，
     * 在该方法中，我们首先开始事务，然后执行：result = method.invoke(proxy, objects)
     */
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable
    {
        transactionManager.start();
        Object result = null;
        try
        {
            result = method.invoke(proxy, objects);
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
        return result;
    }
}
