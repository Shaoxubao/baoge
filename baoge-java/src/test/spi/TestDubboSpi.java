package spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import spi_demo.dubbo.AdaptiveExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/27 15:43
 */
public class TestDubboSpi {

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

}
