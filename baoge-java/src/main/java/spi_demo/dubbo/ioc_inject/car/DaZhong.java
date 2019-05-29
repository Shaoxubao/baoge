package spi_demo.dubbo.ioc_inject.car;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import spi_demo.dubbo.Car;

/**
 * @Author shaoxubao
 * @Date 2019/5/29 15:23
 */

@Activate(group = "black")
public class DaZhong implements Car {
    @Override
    public void day(URL url) {
        System.out.println("大众黑色来也");
    }
}
