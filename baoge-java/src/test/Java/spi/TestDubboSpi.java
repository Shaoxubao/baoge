package spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import spi_demo.dubbo.ActivateExt;
import spi_demo.dubbo.AdaptiveExt;
import spi_demo.dubbo.World;

import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2019/5/27 15:43
 */
public class TestDubboSpi {

    ////////////////////////////@Adaptive test/////////////////////////////
    /**
     * SPI注解中有value值
     */
    @Test
    public void test1() {
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt adaptiveExtension = loader.getAdaptiveExtension();

        URL url = URL.valueOf("test://localhost/test");
        String result = adaptiveExtension.echo("d", url);
        System.out.println(result);


        // 调用方式二
        AdaptiveExt defaultExtension = ExtensionLoader.getExtensionLoader(AdaptiveExt.class).getDefaultExtension();
        System.out.println(defaultExtension.echo("d", url));

    }

    /**
     * SPI注解中有value值，URL中也有具体的值,接口类的方法上有@Adaptive
     */
    @Test
    public void test2() {
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt adaptiveExtension = loader.getAdaptiveExtension();

        URL url = URL.valueOf("test://localhost/test?adaptive.ext=springboot");
        String result = adaptiveExtension.echo("d", url);
        System.out.println(result);
    }

    /**
     * SPI注解中有value值，URL中也有具体的值,实现类(SpringCloudAdaptiveExt)上有@Adaptive注解
     */
    @Test
    public void test3() {
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt adaptiveExtension = loader.getAdaptiveExtension();

        URL url = URL.valueOf("test://localhost/test?adaptive.ext=springboot");
        String result = adaptiveExtension.echo("d", url);
        System.out.println(result + "");
    }

    /**
     * SPI注解中有value值,实现类上没有@Adaptive注解，在方法上打上@Adaptive注解，注解中的value与链接中的参数的key一致，
     * 链接中的key对应的value就是spi中的name,获取相应的实现类
     */
    @Test
    public void test4() {
        ExtensionLoader<AdaptiveExt> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt.class);
        AdaptiveExt adaptiveExtension = loader.getAdaptiveExtension();

        URL url = URL.valueOf("test://localhost/test?t=springboot");
        String result = adaptiveExtension.echo("d", url);
        System.out.println(result + "");
    }

    ////////////////////////////@Activate test/////////////////////////////
    /**
     * @Activate注解中声明group
     */
    @Test
    public void testActivateDefault() {
        ExtensionLoader<ActivateExt> loader = ExtensionLoader.getExtensionLoader(ActivateExt.class);
        URL url = URL.valueOf("test://localhost/test");
        // 查询组为default_group的ActivateExt1的实现
        List<ActivateExt> list = loader.getActivateExtension(url, new String[]{}, "default_group");
        System.out.println(list.size());
        System.out.println(list.get(0).getClass());
    }

    /**
     * @Activate注解中声明多个group
     */
    @Test
    public void testActivate2() {
        URL url = URL.valueOf("test://localhost/test");
        // 查询组为group2的ActivateExt1的实现
        List<ActivateExt> list = ExtensionLoader.getExtensionLoader(ActivateExt.class).getActivateExtension(url, new String[]{}, "group2");
        System.out.println(list.size());
        System.out.println(list.get(0).getClass());
    }

    /**
     * @Activate注解中声明了group与value
     *   这个方法getActivateExtension的value参数，直接通过扩展点名获取扩展点实现（不需要有@Activate注解）。
     *   而group参数，针对的是有@Activate注解的扩展点实现，
     *   首先扩展点实现的@Activate注解的group属性需要匹配，其次@Activate的value属性需要匹配传入的url，
     *   也就时@Activate的value需要在url中至少找到一个匹配的key。group和value的匹配相互独立，取并集。
     */
    @Test
    public void testValue() {
        URL url = URL.valueOf("test://localhost/test");
        // 根据   key = value1,group =  value
        // @Activate(value = {"value1"}, group = {"value"})来激活扩展
        url = url.addParameter("value1", "value");
        List<ActivateExt> list = ExtensionLoader.getExtensionLoader(ActivateExt.class).getActivateExtension(url, new String[]{}, "value");
        System.out.println(list.size());
        System.out.println(list.get(0).getClass());
    }

    /**
     * @Activate注解中声明了order,低的排序优先级搞
     */
    @Test
    public void testOrder() {
        URL url = URL.valueOf("test://localhost/test");
        List<ActivateExt> list = ExtensionLoader.getExtensionLoader(ActivateExt.class).getActivateExtension(url, new String[]{}, "order");
        System.out.println(list.size());
        System.out.println(list.get(0).getClass());
        System.out.println(list.get(1).getClass());
    }

    ///////////////////ioc test//////////////////////////
    @Test
    public void testIoc() {
        World world = ExtensionLoader.getExtensionLoader(World.class).getDefaultExtension();
        // 扩展点自适配 person的value不同 调用不同实现类
//        world.day(URL.valueOf("test://0.0.0.0:8888?person=coder"));
        System.out.println();
//        world.day(URL.valueOf("test://0.0.0.0:8888?person=beauty&color=baoma"));
        System.out.println();
        world.day(URL.valueOf("test://0.0.0.0:8888?person=beauty&color=dazhong"));




        // ==============group value 一起过滤==================
        URL url = URL.valueOf("test://0.0.0.0:8888?person=beauty&baoma1=baoma");

        world.day(url);
    }

}
