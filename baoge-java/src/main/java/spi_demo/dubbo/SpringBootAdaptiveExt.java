package spi_demo.dubbo;

import com.alibaba.dubbo.common.URL;

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
