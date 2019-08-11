package com.baoge.springboot.config.MyAppConfig;

import com.baoge.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/8/11
 *
 * @Configuration:指名当前类是一个配置类；就是来代替之前Spring的配置文件
 * 在配置文件用<bean><bean/>标签添加组件
 *
 */

@Configuration
public class MyAppConfig {

    // 将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
    @Bean
    public HelloService helloService() {
        System.out.println("配置类@Bean给容器中添加组件。");
        return new HelloService();
    }
}
