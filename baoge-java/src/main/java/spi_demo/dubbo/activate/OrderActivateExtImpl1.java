package spi_demo.dubbo.activate;

import com.alibaba.dubbo.common.extension.Activate;
import spi_demo.dubbo.ActivateExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/28 18:01
 */

@Activate(order = 2, group = {"order"})
public class OrderActivateExtImpl1 implements ActivateExt {
    @Override
    public String echo(String msg) {
        return msg;
    }
}
