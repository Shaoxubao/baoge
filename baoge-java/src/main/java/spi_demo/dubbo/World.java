package spi_demo.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("earth")
public interface World {

    void day(URL url);

}
