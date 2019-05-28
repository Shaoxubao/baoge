package spi_demo.dubbo.activate;

import com.alibaba.dubbo.common.extension.Activate;
import spi_demo.dubbo.ActivateExt;

/**
 * @Author shaoxubao
 * @Date 2019/5/28 17:58
 */

@Activate(group = {"default_group"})
public class ActivateExt1Impl1 implements ActivateExt {

    @Override
    public String echo(String msg) {
        return msg;
    }

}
