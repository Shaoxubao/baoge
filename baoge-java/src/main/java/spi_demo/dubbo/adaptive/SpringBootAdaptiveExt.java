package spi_demo.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;
import spi_demo.dubbo.AdaptiveExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/27 14:55
 */


public class SpringBootAdaptiveExt implements AdaptiveExt {

    @Override
    public String echo(String msg, URL url) {
        return "spring boot";
    }
}
