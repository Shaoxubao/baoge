package spi_demo.dubbo.activate;

import com.alibaba.dubbo.common.extension.Activate;
import spi_demo.dubbo.ActivateExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/28 18:03
 */

@Activate(value = {"value1"}, group = {"value"})
public class ValueActivateExtImpl implements ActivateExt {
    @Override
    public String echo(String msg) {
        return msg;
    }
}
