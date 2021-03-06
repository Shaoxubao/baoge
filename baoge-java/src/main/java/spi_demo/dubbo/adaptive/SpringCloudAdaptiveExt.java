package spi_demo.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import spi_demo.dubbo.AdaptiveExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/27 14:55
 */


//@Adaptive
public class SpringCloudAdaptiveExt implements AdaptiveExt {
    @Override
    public String echo(String msg, URL url) {
        return "spring cloud";
    }
}
