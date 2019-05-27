参考：https://www.jianshu.com/p/dc616814ce98

1、dubbo spi 实现方式
    dubbo.internal目录添加spi_demo.dubbo.AdaptiveExt文件
    文件里添加对应实现类路径
    
2、Java spi 实现方式
    services 目录添加spi_demo.java.IHelloService文件
    文件里添加对应实现类路径
    
3、结论
    从上面的几个测试用例，可以得到下面的结论：
    1. 在类上加上@Adaptive注解的类，是最为明确的创建对应类型Adaptive类。 所以他优先级最高。
    2. @SPI注解中的value是默认值，如果通过URL获取不到关于取哪个类作为Adaptive类的话，就使用这个默认值，当然如果URL中可以获取到，就用URL中的。
    3. 可以再方法上增加@Adaptive注解，注解中的value与链接中的参数的key一致，链接中的key对应的value就是spi中的name,获取相应的实现类。
    