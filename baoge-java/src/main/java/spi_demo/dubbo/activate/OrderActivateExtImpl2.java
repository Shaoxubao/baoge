package spi_demo.dubbo.activate;

import com.alibaba.dubbo.common.extension.Activate;
import spi_demo.dubbo.ActivateExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/28 18:02
 */

@Activate(order = 1, group = {"order"})
public class OrderActivateExtImpl2 implements ActivateExt {
    @Override
    public String echo(String msg) {
        return msg;
    }
}
