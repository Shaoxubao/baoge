package com.baoge.generic;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author shaoxubao
 * @Date 2019/6/19 11:32
 *
 * 泛化调用
 *
 */
public class GenericConsumer {

    /**
     * 通过API方式:
     * 运行时启动zk,启动服务提供者provider
     */
    @Test
    public void testGeneric() {
        // 引用远程服务
        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();

        // 弱类型接口名
        reference.setInterface("com.baoge.demo.DemoService");
        reference.setVersion("1.0.0");
        // 声明为泛化接口
        reference.setGeneric(true);

        // 应用名称
        ApplicationConfig application = new ApplicationConfig("demo-consumer");
        reference.setApplication(application);
        // 初始化dubbo注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol("zookeeper");
        registry.setAddress("127.0.0.1:2181");
        reference.setRegistry(registry);

        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用
        GenericService genericService = reference.get();
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke("sayHello", new String[] {"java.lang.String"}, new Object[] {"nezha-world"});
        System.out.println("=================" + result);
    }

    /**
     * Spring配置方式，ClassPathXmlApplicationContext
     */
    @Test
    public void testGeneric2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/*.xml"});
        context.start();
        GenericService genericService = (GenericService) context.getBean("demoService");
        Object result = genericService.$invoke("sayHello", new String[] {"java.lang.String"}, new Object[] {"nezha-world"});
        System.out.println("=================" + result);
    }

}
