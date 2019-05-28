package spi_demo.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author shaoxubao
 * @Date 2019/5/28 17:56
 */

@SPI
public interface ActivateExt {

    String echo(String msg);

}
