package spi_demo.dubbo.ioc_inject;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import spi_demo.dubbo.Car;
import spi_demo.dubbo.Person;

import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/5/29 15:11
 */

public class Beauty implements Person {

    @Override
    public void day(URL url) {
        // 扩展点自动激活，参数key是url带的等号=左边的参数，随后根据key获取url中的value，
        // value参数，直接通过扩展点名获取扩展点实现（不需要有@Activate注解）
        // 而group参数，针对的是有@Activate注解的扩展点实现，
        // 首先扩展点实现的@Activate注解的group属性需要匹配，其次@Activate的value属性需要匹配传入的url，
        // 也就是@Activate的value需要在url中至少找到一个匹配的key。group和value的匹配相互独立，取并集。

        List<Car> pets = ExtensionLoader.getExtensionLoader(Car.class).getActivateExtension(url,"color","red");

        // testIoc测试用例使用
//        List<Car> pets = ExtensionLoader.getExtensionLoader(Car.class).getActivateExtension(url, new String[]{},"white");

        pets.stream().forEach(a -> a.day(url));
        System.out.println("洗车");
    }
}
