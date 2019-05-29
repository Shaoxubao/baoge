package spi_demo.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author shaoxubao
 * @Date 2019/5/29 15:11
 */

@SPI("beauty")
public interface Person {

    @Adaptive("person")
    void day(URL url);

}
