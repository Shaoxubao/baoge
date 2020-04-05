package transaction_aop.factory;

import transaction_aop.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/5
 */
public class ProxyFactoryBean {

    // 事务通知管理
    private TransactionManager transactionManager;

    // 目标对象
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // 传入目标对象target，为它装配好通知，返回代理对象
    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            // 开启事务
                            System.out.println("开启事务...");
                            transactionManager.beginTransaction();

                            // 执行业务操作
                            Object result = method.invoke(target, args);

                            // 提交事务
                            System.out.println("提交事务...");
                            transactionManager.commit();

                            return result;
                        } catch (Exception e) {
                            // 回滚事务
                            System.out.println("回滚事务...");
                            transactionManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            // 释放连接
                            System.out.println("释放连接...");
                            transactionManager.release();
                        }
                    }
                }
        );
        return proxy;
    }
}
