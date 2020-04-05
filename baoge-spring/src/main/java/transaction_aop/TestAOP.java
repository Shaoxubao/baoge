package transaction_aop;

import transaction_aop.factory.BeanFactory;
import transaction_aop.service.UserService;
import transaction_aop.service.UserServiceImpl;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/5
 */
public class TestAOP {

    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        try {
            Object bean = beanFactory.getBean("transaction_aop.service.UserServiceImpl");

            // UserServiceImpl加上@MyTransactional注解，返回的是代理类对象实例
            System.out.println(bean.getClass().getName());

            // UserServiceImpl不加@MyTransactional注解，返回的是transaction_aop.service.UserServiceImpl
//            UserService service = (UserServiceImpl)bean;

            // UserServiceImpl加上@MyTransactional注解，返回的是代理类对象实例
            UserService service = (UserService) bean;
            service.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
