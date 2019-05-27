package spi_demo.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author shaoxubao
 * @Date 2019/5/27 14:40
 */

//@SPI
@SPI("springmvc")
public interface AdaptiveExt {

//    @Adaptive({"t"})
    @Adaptive
    String echo(String msg, URL url);

}
