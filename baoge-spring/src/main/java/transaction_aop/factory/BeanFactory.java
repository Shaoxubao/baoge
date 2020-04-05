package transaction_aop.factory;

import transaction_aop.annotation.MyTransactional;
import transaction_aop.utils.ConnectionUtils;
import transaction_aop.utils.TransactionManager;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/5
 */
public class BeanFactory {

    public Object getBean(String name) throws Exception {
        // 得到目标类的Class对象
        Class<?> clazz = Class.forName(name);
        // 得到目标对象
        Object bean = clazz.newInstance();
        // 得到目标类上的@MyTransactional注解
        MyTransactional myTransactional = clazz.getAnnotation(MyTransactional.class);
        // 如果打了@MyTransactional注解，返回代理对象，否则返回目标对象
        if (null != myTransactional) {
            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            TransactionManager txManager = new TransactionManager();
            txManager.setConnectionUtils(new ConnectionUtils());
            // 装配通知和目标对象
            proxyFactoryBean.setTransactionManager(txManager);
            proxyFactoryBean.setTarget(bean);
            Object proxyBean = proxyFactoryBean.getProxy();
            // 返回代理对象
            return proxyBean;
        }
        // 返回目标对象
        return bean;
    }

}
